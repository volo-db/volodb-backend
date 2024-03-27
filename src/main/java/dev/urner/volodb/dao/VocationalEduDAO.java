package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VocationalEdu;

@Repository
public interface VocationalEduDAO extends ListCrudRepository<VocationalEdu, Integer> {

  public List<VocationalEdu> findAll();

  public VocationalEdu findById(int id);

  public VocationalEdu findByName(String vocationalEduName);

}
