package org.co.WorkSearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure OpenAPI documentation
 */
@Configuration
public class OpenAPIConfig {
        /**
         * The OpenAPI documentation configuration
         * @param buildProperties {@link BuildProperties} bean which contains gradle build properties.
         * @return The OpenAPI documentation bean.
         */
        @Bean
        public OpenAPI apiDocConfig(BuildProperties buildProperties) {
                return new OpenAPI()
                        .info(new Info()
                                .title("Work Search")
                                .description("Record your current work search.")
                                .version(buildProperties.getVersion())
                                .contact(new Contact()
                                        .name("Courtney Oliver")
                                        .email("coliverhb@gmail.com")));
        }
}
