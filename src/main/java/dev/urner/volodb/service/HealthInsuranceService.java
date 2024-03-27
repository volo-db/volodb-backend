package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.HealthInsuranceDAO;
import dev.urner.volodb.entity.HealthInsurance;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthInsuranceService {

  private final HealthInsuranceDAO healthInsuranceDAO;

  public HealthInsurance findByName(String insuranceName) {
    return healthInsuranceDAO.findByName(insuranceName);
  }

}
