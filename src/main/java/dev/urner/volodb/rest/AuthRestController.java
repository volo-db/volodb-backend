package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;

import dev.urner.volodb.entity.User;
import dev.urner.volodb.entity.UserNotFoundException;
import dev.urner.volodb.model.LoginRequest;
import dev.urner.volodb.model.LoginResponse;
import dev.urner.volodb.security.JwtIssuer;
import dev.urner.volodb.security.UserPrincipal;
import dev.urner.volodb.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
  
  private final JwtIssuer jwtIssuer;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;


  @GetMapping("/devInfo")
  public User devInfo(@AuthenticationPrincipal UserPrincipal principal) {
    User theUser = userService.findByUsername(principal.getUsername());
    
    if(theUser == null) {
      throw new UserNotFoundException("Username not found - " + principal.getUsername());
    }

    return theUser;
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

  @ExceptionHandler
  public ResponseEntity<AuthErrorResponse> handleException(UserNotFoundException exc) {
    AuthErrorResponse error = new AuthErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      exc.getMessage(),
      System.currentTimeMillis());

      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler
  public ResponseEntity<AuthErrorResponse> handleException(TokenExpiredException exc) {
    AuthErrorResponse error = new AuthErrorResponse(
      HttpStatus.UNAUTHORIZED.value(),
      exc.getMessage(),
      System.currentTimeMillis());

      return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  
  
}
