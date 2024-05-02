package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.User;
import dev.urner.volodb.entity.UserNotFoundException;
import dev.urner.volodb.security.UserPrincipal;
import dev.urner.volodb.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  @GetMapping()
  public User getUser(@AuthenticationPrincipal UserPrincipal principal) {
    User theUser = userService.findByUsername(principal.getUsername());

    if (theUser == null) {
      throw new UserNotFoundException("Username not found - " + principal.getUsername());
    }

    return theUser;
  }

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(UserNotFoundException exc) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

}
