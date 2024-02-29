package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.User;

public interface UserDAO {
    
  List<User> findAll();

    User findByUsername(String theUsername);

    User save(User theUser);

    void deleteById(int theId);
}
