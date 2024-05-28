package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.ContractModification;

@Repository
public interface ContractModificationDAO extends ListCrudRepository<ContractModification, Integer> {

  List<ContractModification> findAll();

  ContractModification findById(int id);

  List<ContractModification> findAllByContractId(int contractId);

}
