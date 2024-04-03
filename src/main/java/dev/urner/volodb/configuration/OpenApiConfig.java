package dev.urner.volodb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI api() {
    return new OpenAPI()
        .info(
            new Info()
                .title("VoloDB API")
                .version("v0.0.1")
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")));
  }
}
