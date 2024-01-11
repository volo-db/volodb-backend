package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAOJpaImpl implements UserDAO {

  private final EntityManager entityManager;

  @Override
  public List<User> findAll() {
    TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

    List<User> users = theQuery.getResultList();

    return users;
  }

  @Override
  public User findByUsername(String theUsername) {
    User theUser = entityManager.find(User.class, theUsername);
    return theUser;
  }

  @Override
  public User save(User theUser) {
    User dbUser = entityManager.merge(theUser);

    return dbUser;
  }

  @Override
  public void deleteById(int theId) {
    User theUser = entityManager.find(User.class, theId);

    entityManager.remove(theUser);
  }
  
}
