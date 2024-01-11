package dev.urner.volodb.service;

import java.util.List;
import java.util.Optional;

import dev.urner.volodb.entity.User;

public interface UserService {
    List<User> findAll();

    User findByUsername(String theUsername);

    User save(User theUser);

    void deleteById(int theId);
}
