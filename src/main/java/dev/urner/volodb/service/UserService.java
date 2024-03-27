package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.UserDAO;
import dev.urner.volodb.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO userDAO;

  public List<User> findAll() {
    return userDAO.findAll();
  }

  public User findByUsername(String theUsername) {
    return userDAO.findByUsername(theUsername);

  }

  @Transactional
  public User save(User theUser) {
    return userDAO.save(theUser);
  }

}
