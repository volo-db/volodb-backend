package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Contract;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ContractDAOJpaImpl implements ContractDAO {

  private final EntityManager entityManager;

  @Override
  public List<Contract> findAll() {
    TypedQuery<Contract> query = entityManager.createQuery("from Contract", Contract.class);
    return query.getResultList();
  }

  @Override
  public Contract findById(int contractId) {
    return entityManager.find(Contract.class, contractId);
  }

  @Override
  public List<Contract> findByVolunteerId(int volunteerId) {
    TypedQuery<Contract> query = entityManager
        .createQuery(
            "SELECT c " +
                "FROM Contract c " +
                "WHERE c.volunteer.id = :volunteerId",
            Contract.class)
        .setParameter("volunteerId", volunteerId);
    return query.getResultList();
  }

}
