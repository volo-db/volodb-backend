package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Religion;

@Repository
public interface ReligionDAO extends ListCrudRepository<Religion, Integer> {

  public List<Religion> findAll();

  public Religion findByName(String name);

  public Religion findByShorthand(String shorthand);

}
