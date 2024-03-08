package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VocationalEdu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VocationalEduDAOJpaImpl implements VocationalEduDAO {

  private final EntityManager entityManager;

  @Override
  public List<VocationalEdu> findAll() {
    TypedQuery<VocationalEdu> query = entityManager.createQuery("from VocationalEdu", VocationalEdu.class);
    return query.getResultList();
  }

  @Override
  public VocationalEdu findById(int vocationalEduId) {
    return entityManager.find(VocationalEdu.class, vocationalEduId);
  }

  @Override
  public VocationalEdu findByName(String vocationalEduName) {
    TypedQuery<VocationalEdu> query = entityManager
        .createQuery("SELECT ve FROM VocationalEdu ve WHERE ve.name = :veName", VocationalEdu.class)
        .setParameter("veName", vocationalEduName);

    return query.getResultList().get(0);
  }

}
