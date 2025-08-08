package org.co.WorkSearch.config;

import lombok.extern.slf4j.Slf4j;
import org.co.WorkSearch.config.properties.CorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Global CORS configuration.
 */
@Configuration
@Slf4j
public class CorsConfiguration {
    /**
     * Set CORS configurations based upon paths.
     * This is configured via the {@link CorsProperties} Configuration properties class, which has it's values set in
     * application.yaml.
     * @return The {@link CorsConfigurationSource} bean.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        corsProperties.getPaths().forEach(path -> {
            log.info("Adding CorsConfiguration for path {}", path);

            org.springframework.web.cors.CorsConfiguration configuration =
                    new org.springframework.web.cors.CorsConfiguration();

            configuration.setAllowCredentials(path.allowCredentials());
            configuration.setAllowedOriginPatterns(path.allowedOrigins());
            configuration.setAllowedMethods(path.allowedMethods());
            configuration.setAllowedHeaders(path.allowedHeaders());

            source.registerCorsConfiguration(path.path(), configuration);
        });

        return source;
    }
}
