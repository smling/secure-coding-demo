package io.github.smling.securecodingdemo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class SecurityConfiguration {
    @Bean
    public WebFluxConfigurer corsMappingConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://localhost:8080")
                        .allowedMethods("GET", "POST")
                        .allowCredentials(true).maxAge(3600);

            }
        };
    }
}
