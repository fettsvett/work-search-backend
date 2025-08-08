package org.co.WorkSearch.config;

import lombok.extern.slf4j.Slf4j;
import org.co.WorkSearch.config.properties.CorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Slf4j
public class CorsConfiguration {
    /**
     * CORS configuration properties.
     * @return The configuration source
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        corsProperties.getPaths().forEach(path -> {
            log.info("Adding CorsConfiguration for path {}", path);
            org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
            configuration.setAllowCredentials(path.isAllowCredentials());
            configuration.setAllowedOriginPatterns(path.getAllowedOrigins());
            configuration.setAllowedMethods(path.getAllowedMethods());
            configuration.setAllowedHeaders(path.getAllowedHeaders());
            source.registerCorsConfiguration(path.getPath(), configuration);
        });

        return source;
    }
}
