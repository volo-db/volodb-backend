package dev.urner.volodb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Add your allowed origins here
            .allowedMethods("*") // Add your allowed methods
            .allowedHeaders("*") // Add your allowed headers
            .allowCredentials(true)
            .maxAge(3600); // Max age of the pre-flight request in seconds
      }
    };
  }
}
