package dev.urner.volodb.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dev.urner.volodb.service.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userService.findByUsername(username);

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    authorities = user.getRoles().stream().map((role) -> { 
      return new SimpleGrantedAuthority(role.getName());
     })
     .collect(Collectors.toList());



    return UserPrincipal.builder()
      .username(user.getUsername())
      .authorities(authorities)
      .secret(user.getSecret())
      .build();
  }
  
}
