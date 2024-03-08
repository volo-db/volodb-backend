package dev.urner.volodb.service;

import dev.urner.volodb.entity.HealthInsurance;

public interface HealthInsuranceService {
  HealthInsurance findByName(String insuranceName);
}
