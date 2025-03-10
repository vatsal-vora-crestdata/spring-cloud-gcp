package com.google.cloud.spring.parametermanager;

import com.google.cloud.parametermanager.v1.ParameterVersionName;
import com.google.cloud.spring.core.GcpProjectIdProvider;
import org.springframework.util.Assert;

/** Utilities for parsing Parameter Manager properties. */
public class ParameterManagerPropertyUtils {

    private static final String GCP_PARAMETER_PREFIX = "pm@";

    private ParameterManagerPropertyUtils() {}

    static ParameterVersionName getParameterVersionName(final String input, GcpProjectIdProvider projectIdProvider) {
        // Since spring-core 6.2.2, the property resolution mechanism will try a full match that
        // may include a default string if provided. For example, a @Value("${pm@parameter:default}") will
        // cause two attempts: one with pm@parameter:default as a whole string (we don't want this),
        // and one with pm@parameter (that's the one we want to process). The colon is also an invalid
        // character in parameter IDs.
        final boolean isAttemptingFullStringMatch = input.contains(":");
        if (!input.startsWith(GCP_PARAMETER_PREFIX) || isAttemptingFullStringMatch) {
            return null;
        }

        String resourcePath = input.substring(GCP_PARAMETER_PREFIX.length());
        String[] tokens = resourcePath.split("/");

        String projectId = projectIdProvider.getProjectId();
        String locationId = "global";
        String parameterId = null;
        String versionId = null;

        if (tokens.length == 2) {
            // property is of the form "pm@<parameter-id>/<version-id>"
            parameterId = tokens[0];
            versionId = tokens[1];
        } else if (tokens.length == 3) {
            // property is of the form "pm@<project-id>/<parameter-id>/<version-id>"
            projectId = tokens[0];
            parameterId = tokens[1];
            versionId = tokens[2];
        } else if (tokens.length == 4 && tokens[0].equals("locations")) {
            // property is of the form "pm@locations/<location-id>/<parameter-id>/<version-id>"
            locationId = tokens[1];
            parameterId = tokens[2];
            versionId = tokens[3];
        } else if (tokens.length == 4) {
            // property is of the form "pm@<project-id>/<location-id>/<parameter-id>/<version-id>"
            projectId = tokens[0];
            locationId = tokens[1];
            parameterId = tokens[2];
            versionId = tokens[3];
        } else if (tokens.length == 6 && tokens[0].equals("locations")
                && tokens[2].equals("parameters")
                && tokens[4].equals("versions")) {
            // property is of the form "pm@locations/<location-id>/parameters/<parameter-id>/versions/<version-id>"
            locationId = tokens[1];
            parameterId = tokens[3];
            versionId = tokens[5];
        } else if (tokens.length == 6 && tokens[0].equals("projects")
                && tokens[2].equals("parameters")
                && tokens[4].equals("versions")) {
            // property is of the form "pm@projects/<project-id>/parameters/<parameter-id>/versions/<version-id>"
            projectId = tokens[1];
            parameterId = tokens[3];
            versionId = tokens[5];
        } else if (tokens.length == 8
                && tokens[0].equals("projects")
                && tokens[2].equals("locations")
                && tokens[4].equals("parameters")
                && tokens[6].equals("versions")) {
            // property is of the form
            // "pm@projects/<project-id>/locations/<location-id>/parameters/<parameter-id>/versions/<version-id>"
            projectId = tokens[1];
            locationId = tokens[3];
            parameterId = tokens[5];
            versionId = tokens[7];
        } else {
            throw new IllegalArgumentException("Unrecognized format for specifying a GCP Parameter Manager parameter: " + input);
        }

        Assert.hasText(projectId, "The GCP Parameter Manager project id must not be empty: " + input);

        Assert.hasText(locationId, "The GCP Parameter Manager location id must not be empty: " + input);

        Assert.hasText(parameterId, "The GCP Parameter Manager parameter id must not be empty: " + input);

        Assert.hasText(versionId, "The GCP Parameter Manager parameter version must not be empty: " + input);

        return ParameterVersionName.of(projectId, locationId, parameterId, versionId);
    }
}
