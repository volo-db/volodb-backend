package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Contract;

@Repository
public interface ContractDAO extends ListCrudRepository<Contract, Integer> {

  List<Contract> findAll();

  Contract findById(int id);

  List<Contract> findAllByVolunteerId(int volunteerId);

}
