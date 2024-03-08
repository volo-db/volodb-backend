package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Religion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReligionDAOJpaImpl implements ReligionDAO {

  private final EntityManager entityManager;

  @Override
  public List<Religion> findAll() {
    TypedQuery<Religion> query = entityManager.createQuery("from Religion", Religion.class);
    return query.getResultList();
  }

  @Override
  public Religion findByName(String religionName) {
    TypedQuery<Religion> query = entityManager
        .createQuery("SELECT r FROM Religion r WHERE r.name = :rName", Religion.class)
        .setParameter("rName", religionName);

    return query.getResultList().get(0);
  }

  @Override
  public Religion findByShorthand(String religionShorthand) {
    TypedQuery<Religion> query = entityManager
        .createQuery("SELECT r FROM Religion r WHERE r.shorthand = :rShorthand", Religion.class)
        .setParameter("rShorthand", religionShorthand);

    return query.getResultList().get(0);
  }

}
