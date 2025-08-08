package org.co.WorkSearch.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "work-search.cors")
public class CorsProperties {
    private String path;
    private boolean allowCredentials;
    private List<String> allowedHeaders;
    private List<String> allowedOrigins;
    private List<String> allowedMethods;
}
