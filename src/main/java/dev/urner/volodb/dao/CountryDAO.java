package dev.urner.volodb.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Country;

@Repository
public interface CountryDAO extends ListCrudRepository<Country, Integer> {

  public Country findByLocalName(String name);

  public Country findByNationality(String nationality);

}
