package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.User;
import dev.urner.volodb.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
public class UserRestController {
  private final UserService userService;

  @GetMapping("/user")
  public List<User> getUser() {
      return userService.findAll();
  }
  
}
