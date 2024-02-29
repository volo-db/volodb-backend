package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.UserDAO;
import dev.urner.volodb.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;

  @Override
  public List<User> findAll() {
    return userDAO.findAll();
  }

  @Override
  public User findByUsername(String theUsername) {
    return userDAO.findByUsername(theUsername);
    
  }

  @Transactional
  @Override
  public User save(User theUser) {
    return userDAO.save(theUser);
  }

  @Transactional
  @Override
  public void deleteById(int theId) {
    userDAO.deleteById(theId);
  }

  
}
