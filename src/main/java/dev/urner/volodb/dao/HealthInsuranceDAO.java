package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.HealthInsurance;

@Repository
public interface HealthInsuranceDAO extends ListCrudRepository<HealthInsurance, Integer> {

  public List<HealthInsurance> findAll();

  public HealthInsurance findByName(String insuranceName);

}
