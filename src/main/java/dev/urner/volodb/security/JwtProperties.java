package dev.urner.volodb.security;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
  /**
   * Secret key used for issuing JWT
   */
  private String secretKey;
  private Duration tokenDuration;

}
