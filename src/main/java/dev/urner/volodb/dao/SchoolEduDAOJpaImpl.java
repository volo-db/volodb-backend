package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.SchoolEdu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SchoolEduDAOJpaImpl implements SchoolEduDAO {

  private final EntityManager entityManager;

  @Override
  public List<SchoolEdu> findAll() {
    TypedQuery<SchoolEdu> query = entityManager.createQuery("from SchoolEdu", SchoolEdu.class);

    return query.getResultList();
  }

  @Override
  public SchoolEdu findById(int schoolEduId) {
    return entityManager.find(SchoolEdu.class, schoolEduId);
  }

  @Override
  public SchoolEdu findByName(String schoolEduName) {
    TypedQuery<SchoolEdu> query = entityManager
        .createQuery("SELECT se FROM SchoolEdu se WHERE se.name = :seName", SchoolEdu.class)
        .setParameter("seName", schoolEduName);

    return query.getResultList().get(0);

  }

}
