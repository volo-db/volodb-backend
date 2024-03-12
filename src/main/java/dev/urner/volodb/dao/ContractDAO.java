package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.Contract;

public interface ContractDAO {
  List<Contract> findAll();

  Contract findById(int contractId);

  List<Contract> findByVolunteerId(int volunteerId);
}
