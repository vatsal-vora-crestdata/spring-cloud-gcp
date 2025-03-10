package com.google.cloud.spring.parametermanager;

import java.util.Optional;

/**
 *
 */
public class ParameterManagerSyntaxUtils {
    // Prefix for Google Cloud Parameter Manager resources.
    private static final String PREFIX = "pm@";

    public static Optional<String> getMatchedPrefixes(PrefixMatcher matcher) {
        return Optional.of(PREFIX).filter(matcher::matches);
    }

    @FunctionalInterface
    public interface PrefixMatcher {
        boolean matches(String input);
    }
}
