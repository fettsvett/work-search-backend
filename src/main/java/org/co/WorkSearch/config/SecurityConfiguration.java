package org.co.WorkSearch.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Global Security configuration.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    /**
     * Configure the security filter chain.
     * @param http The HttpSecurity builder.
     * @return A {@link SecurityFilterChain} bean.
     * @throws Exception any exception.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.OPTIONS).permitAll() // Allow CORS options requests.
                        .requestMatchers(HttpMethod.POST, "/account").permitAll()
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() // Allow errors to be processed into responses
                        .anyRequest().authenticated())
                .build();
    }
}
