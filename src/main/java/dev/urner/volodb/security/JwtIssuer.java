package dev.urner.volodb.security;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

  private final JwtProperties properties;
  
  public String issue(String username, List<String> roles) {
    var now = Instant.now();

    return JWT.create()
      .withSubject(String.valueOf(username))
      .withIssuedAt(now)
      .withExpiresAt(now.plus(properties.getTokenDuration()))
      .withClaim("authorities", roles)
      .sign(Algorithm.HMAC256(properties.getSecretKey()));
  }
}
