package dev.urner.volodb.service;

import java.util.List;

import dev.urner.volodb.entity.Contract;

public interface ContractService {
  List<Contract> findAll();

  Contract findById(int contractId);

  List<Contract> findByVolunteerId(int volunteerId);
}
