package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.HealthInsurance;

public interface HealthInsuranceDAO {
  List<HealthInsurance> findAll();

  HealthInsurance findByName(String insuranceName);

  // HealthInsurance save(HealthInsurance insurance);

  // HealthInsurance deleteById(int insuranceId);
}
