package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Country;

@Repository
public interface CountryDAO extends ListCrudRepository<Country, Integer> {

  public List<Country> findAll();

  public Country findByLocalName(String localName);

  public Country findByName(String name);

  public Country findByNationality(String nationality);

}
