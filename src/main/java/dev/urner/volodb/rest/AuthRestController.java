package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.model.LoginRequest;
import dev.urner.volodb.model.LoginResponse;
import dev.urner.volodb.security.JwtIssuer;
import dev.urner.volodb.security.UserPrincipal;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
  
  private final JwtIssuer jwtIssuer;
  @GetMapping("secured")
  public String secured(@AuthenticationPrincipal UserPrincipal principal) {
      return "Hello again! \n\n" +
        "User-ID: " + principal.getUserId() + "\n" +
        "Username: " + principal.getUsername() + "\n" +
        "Email: " + principal.getEmail() + "\n" +
        "Password: " + principal.getPassword() + "\n" +
        "Authoroties: " + principal.getAuthorities() + "\n" +
        "Class: " + principal.getClass();
  }
  

  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
      var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));

      return LoginResponse.builder()
        .accessToken(token)
        .build();
  }
  
}
