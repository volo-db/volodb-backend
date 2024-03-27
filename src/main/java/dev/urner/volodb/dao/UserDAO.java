package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.User;

@Repository
public interface UserDAO extends ListCrudRepository<User, String> {

  public List<User> findAll();

  public User findByUsername(String username);

  public User save(User user);

  public void deleteByUsername(String username);

}
