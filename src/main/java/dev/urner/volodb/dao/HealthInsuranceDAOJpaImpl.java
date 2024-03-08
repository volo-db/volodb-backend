package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.HealthInsurance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HealthInsuranceDAOJpaImpl implements HealthInsuranceDAO {

  private final EntityManager entityManager;

  @Override
  public List<HealthInsurance> findAll() {
    TypedQuery<HealthInsurance> theQuery = entityManager.createQuery("from healthInsurance", HealthInsurance.class);
    return theQuery.getResultList();
  }

  @Override
  public HealthInsurance findByName(String insuranceName) {
    TypedQuery<HealthInsurance> query = entityManager
        .createQuery("SELECT hi FROM HealthInsurance hi WHERE hi.name = :hiName", HealthInsurance.class)
        .setParameter("hiName", insuranceName);

    List<HealthInsurance> insurances = query.getResultList();

    return insurances.get(0);
  }

}
