package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.model.LoginRequest;
import dev.urner.volodb.model.LoginResponse;
import dev.urner.volodb.security.JwtIssuer;
import dev.urner.volodb.security.UserPrincipal;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
  private final AuthenticationManager authenticationManager;


  @GetMapping("secured")
  public String secured(@AuthenticationPrincipal UserPrincipal principal) {
      return "Hello again! \n\n" +
        "Username: " + principal.getUsername() + "\n" +
        "Password: " + principal.getPassword() + "\n" +
        "Authoroties: " + principal.getAuthorities() + "\n" +
        "Class: " + principal.getClass();
  }
  

  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
      var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);

      var principal = (UserPrincipal) authentication.getPrincipal();
      var roles = principal.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toList();

      var token = jwtIssuer.issue(principal.getUsername(), roles);

      return LoginResponse.builder()
        .accessToken(token)
        .build();
  }
  
}
