package dev.urner.volodb.service;

import dev.urner.volodb.entity.Country;

public interface CountryService {
  Country findByName(String countryName);

  Country findByNationalityName(String natianalityName);
}
