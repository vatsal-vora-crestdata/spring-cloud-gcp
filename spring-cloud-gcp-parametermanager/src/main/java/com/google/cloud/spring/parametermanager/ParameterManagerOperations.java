package com.google.cloud.spring.parametermanager;

import com.google.cloud.parametermanager.v1.ParameterFormat;

/**
 * Describes supported operations that one can perform on the Parameter Manager API.
 *
 * <p>For some methods you may specify the secret from GCP Parameter Manager by URI string. The
 * following parameter URI syntax is supported:
 *
 * <p>1. Long form - specify the project ID, location ID, parameter ID, and version ID
 * pm@projects/{project-id}/locations/{location-id}/parameters/{parameter-id}/versions/{version-id}
 *
 * <p>2. Long form - specify project ID, parameter ID, and version ID
 * pm@projects/{project-id}/parameters/{parameter-id}/versions/{version-id}
 *
 * <p>3. Long form - specify location ID, parameter ID, and version ID
 * pm@locations/{location-id}/parameters/{parameter-id}/versions/{version-id}
 *
 * <p>4. Short form - specify project ID, location ID, parameter ID, and version ID
 * pm@{project-id}/{location-id}/{parameter-id}/{version-id}
 *
 * <p>5. Short form - specify location ID, parameter ID, and version ID
 * pm@locations/{location-id}/{parameter-id}/{version-id}
 *
 * <p>6. Short form - specify project ID, parameter ID, and version ID
 * pm@{project-id}/{parameter-id}/{version-id}
 *
 * <p>7. Shortest form - specify parameter ID and version ID, use default GCP project configured
 * pm@{parameter-id}/{version-id}
 */
public interface ParameterManagerOperations {

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     * 
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     */
    void createParameter(String parameterId, String versionId, String payload);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     *
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     */
    void createParameter(String parameterId, String versionId, String payload, ParameterFormat format);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     * 
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     * @param locationId the parameter location string.
     */
    void createParameter(String parameterId, String versionId, String payload, String locationId);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     *
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     * @param locationId the parameter location string.
     * @param projectId unique identifier of your GCP project.
     */
    void createParameter(String parameterId, String versionId, String payload, String locationId, String projectId);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     *
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     * @param locationId the parameter location string.
     */
    void createParameter(String parameterId, String versionId, String payload, ParameterFormat format, String locationId);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     *
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a string.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     * @param locationId the parameter location string.
     * @param projectId unique identifier of your GCP project.
     */
    void createParameter(String parameterId, String versionId, String payload, ParameterFormat format, String locationId, String projectId);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     * 
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a byte array.
     */
    void createParameter(String parameterId, String versionId, byte[] payload);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     *
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a byte array.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     */
    void createParameter(String parameterId, String versionId, byte[] payload, ParameterFormat format);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     * 
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a byte array.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     * @param locationId the parameter location string.
     */
    void createParameter(String parameterId, String versionId, byte[] payload, ParameterFormat format, String locationId);

    /**
     * Creates a new parameter or a new version of existing parameter with the provided {@code payload}.
     * 
     * <p>If there is already a parameter saved in ParameterManager with the specified {@code parameterId},
     * then it simply creates a new version with given id under the parameter with the specified {@code payload}.
     *
     * @param parameterId the parameter ID of the parameter to create.
     * @param versionId the version ID of the parameter version to create.
     * @param payload the parameter payload as a byte array.
     * @param format the parameter format (JSON or YAML or UNFORMATTED).
     * @param locationId the parameter location string.
     * @param projectId unique identifier of your GCP project.
     */
    void createParameter(String parameterId, String versionId, byte[] payload, ParameterFormat format, String locationId, String projectId);

    /**
     * Enables the specified parameter version under the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     */
    void enableParameterVersion(String parameterId, String versionId);

    /**
     * Enables the specified parameter version under the default-configured project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     * @param locationId the location string where given parameter is present.
     */
    void enableParameterVersion(String parameterId, String versionId, String locationId);

    /**
     * Enables the specified parameter version under the specified {@code projectId} for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     * @param locationId the location string where given parameter is present.
     * @param projectId unique identifier of your GCP project.
     */
    void enableParameterVersion(String parameterId, String versionId, String locationId, String projectId);

    /**
     * Disables the specified parameter version under the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     */
    void disableParameterVersion(String parameterId, String versionId);

    /**
     * Disables the specified parameter version under the default-configured project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     * @param locationId the location string where given parameter is present.
     */
    void disableParameterVersion(String parameterId, String versionId, String locationId);

    /**
     * Disables the specified parameter version under the specified {@code projectId} for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to enable.
     * @param versionId the version ID to be enabled.
     * @param locationId the location string where given parameter is present.
     * @param projectId unique identifier of your GCP project.
     */
    void disableParameterVersion(String parameterId, String versionId, String locationId, String projectId);

    /**
     * Deletes the specified {@code parameterId} under the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     */
    void deleteParameter(String parameterId);

    /**
     * Deletes the specified {@code parameterId} under the default-configured project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     * @param locationId the location string where given parameter is present.
     */
    void deleteParameter(String parameterId, String locationId);

    /**
     * Deletes the specified {@code parameterID} under the specified {@code projectId} for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     * @param locationId the location string where given parameter is present.
     * @param projectId unique identifier of your GCP project.
     */
    void deleteParameter(String parameterId, String locationId, String projectId);

    /**
     * Deletes the specified parameter version under the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     * @param versionId the version ID to be deleted.
     */
    void deleteParameterVersion(String parameterId, String versionId);

    /**
     * Deletes the specified parameter version under the default-configured project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     * @param versionId the version ID to be deleted.
     * @param locationId the location string where given parameter is present.
     */
    void deleteParameterVersion(String parameterId, String versionId, String locationId);

    /**
     * Deletes the specified parameter version under the specified {@code projectId} for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to delete.
     * @param versionId the version ID to be deleted.
     * @param locationId the location string where given parameter is present.
     * @param projectId unique identifier of your GCP project.
     */
    void deleteParameterVersion(String parameterId, String versionId, String locationId, String projectId);

    /**
     * Checks if the specified {@code parameterId} exists in the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @return true if the parameter exists in Parameter Manager; false otherwise
     */
    boolean parameterExists(String parameterId);

    /**
     * Checks if the specified {@code parameterID} exists in the default-configured project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @param locationId the location string where given parameter is present.
     * @return true if the parameter exists in Parameter Manager; false otherwise
     */
    boolean parameterExists(String parameterId, String locationId);

    /**
     * Checks if the specified {@code parameterID} exists in the specified project for specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @param locationId the location string where given parameter is present.
     * @param projectId unique identifier of your GCP project.
     * @return true if the parameter exists in Parameter Manager; false otherwise
     */
    boolean parameterExists(String parameterId, String locationId, String projectId);

    /**
     * Checks if the specified parameter version exists in the default-configured project.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @param versionId the version ID of the parameter version to check.
     * @return true if the parameter version exists in Parameter Manager; false otherwise
     */
    boolean parameterVersionExists(String parameterId, String versionId);

    /**
     * Checks if the specified parameter version exists in the default-configured project for the specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @param versionId the version ID of the parameter version to check.
     * @param locationId the location string where the given parameter version is present.
     * @return true if the parameter version exists in Parameter Manager; false otherwise
     */
    boolean parameterVersionExists(String parameterId, String versionId, String locationId);

    /**
     * Checks if the specified parameter version exists in the specified project for the specified {@code locationId}.
     *
     * @param parameterId the parameter ID of the parameter to check.
     * @param versionId the version ID of the parameter version to check.
     * @param locationId the location string where the given parameter version is present.
     * @param projectId unique identifier of your GCP project.
     * @return true if the parameter version exists in Parameter Manager; false otherwise
     */
    boolean parameterVersionExists(String parameterId, String versionId, String locationId, String projectId);

    /**
     * Gets the parameter data as a string from the specified {@code parameterIdentifier}.
     *
     * <p>The {@code parameterIdentifier} must be a fully qualified `pm@` protocol string which specifies
     * the parameter (see javadocs of {@link ParameterManagerOperations} for the protocol format).
     *
     * <p>If the parameter ID string is passed in, then this will return the data of the parameter for
     * the default project at the latest version.
     *
     * @param parameterIdentifier a pm@ formatted string specifying the parameter version.
     * @return The parameter data as a string
     */
    String getParameterString(String parameterIdentifier);

    /**
     * Gets the parameter data as a byte array from the specified {@code parameterIdentifier}.
     *
     * <p>The {@code parameterIdentifier} must be a fully qualified `pm@` protocol string which specifies
     * the parameter (see javadocs of {@link ParameterManagerOperations} for the protocol format).
     *
     * <p>If the parameter ID string is passed in, then this will return the data of the parameter for
     * the default project at the latest version.
     *
     * @param parameterIdentifier a pm@ formatted string specifying the parameter version.
     * @return The parameter data as a byte array
     */
    byte[] getParameterBytes(String parameterIdentifier);

    /**
     * Gets the rendered parameter data as a string from the specified {@code parameterIdentifier}.
     *
     * <p>The {@code parameterIdentifier} must be a fully qualified `pm@` protocol string which specifies
     * the parameter (see javadocs of {@link ParameterManagerOperations} for the protocol format).
     *
     * <p>If the parameter ID string is passed in, then this will return the rendered data of the parameter
     * for the default project at the latest version.
     *
     * @param parameterIdentifier a pm@ formatted string specifying the parameter version.
     * @return The rendered parameter data as a string
     */
    String getRenderedParameterString(String parameterIdentifier);

    /**
     * Gets the rendered parameter data as a byte array from the specified {@code parameterIdentifier}.
     *
     * <p>The {@code parameterIdentifier} must be a fully qualified `pm@` protocol string which specifies
     * the parameter (see javadocs of {@link ParameterManagerOperations} for the protocol format).
     *
     * <p>If the parameter ID string is passed in, then this will return the rendered data of the parameter
     * for the default project at the latest version.
     *
     * @param parameterIdentifier a pm@ formatted string specifying the parameter version.
     * @return The rendered parameter data as a byte array
     */
    byte[] getRenderedParameterBytes(String parameterIdentifier);
}
