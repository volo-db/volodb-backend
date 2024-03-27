package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.SchoolEdu;

@Repository
public interface SchoolEduDAO extends ListCrudRepository<SchoolEdu, Integer> {

  public List<SchoolEdu> findAll();

  public SchoolEdu findById(int id);

  public SchoolEdu findByName(String name);

}
