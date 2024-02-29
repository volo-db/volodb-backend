package dev.urner.volodb.dao;

import dev.urner.volodb.entity.Country;

public interface  CountryDAO {
  Country findByName(String countryName);
}
