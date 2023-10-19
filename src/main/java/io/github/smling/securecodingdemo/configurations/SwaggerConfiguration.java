package io.github.smling.securecodingdemo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
       return new Docket(DocumentationType.OAS_30)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("io.github.smling")) // Set your base package
                    .paths(PathSelectors.any())
                    .build();
      }
}
