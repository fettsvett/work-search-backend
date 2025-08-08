package org.co.WorkSearch.config.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Configuration properties class for configuring CORS globally.
 */
@Value
@ConfigurationProperties(prefix = "work-search.cors")
public class CorsProperties {
    List<Path> paths;

    /**
     * Individual CORS path.
     * @param path The URL pattern we want to match this path against.
     * @param allowCredentials Allow credentials?
     * @param allowedHeaders Allow these headers.
     * @param allowedOrigins Allow these origins.
     * @param allowedMethods Allow these HTTP methods.
     */
    public record Path(
            String path,
            boolean allowCredentials,
            List<String> allowedHeaders,
            List<String> allowedOrigins,
            List<String> allowedMethods
    ) {}
}
