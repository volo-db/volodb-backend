package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Salary;

@Repository
public interface ContractSalaryDAO extends ListCrudRepository<Salary, Integer> {

  List<Salary> findAll();

  Salary findById(int id);

}
