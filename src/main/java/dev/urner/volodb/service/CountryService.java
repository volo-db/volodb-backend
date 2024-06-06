package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.CountryDAO;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.exception.CountryNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryDAO countryDao;

  public List<Country> findAll() {
    return countryDao.findAll();
  }

  public Country findByName(String countryName) {
    return countryDao.findByLocalName(countryName);
  }

  public Country findByNationalityName(String natianalityName) {
    Country myCountry = null;
    try {
      myCountry = countryDao.findByNationality(natianalityName);
    } catch (Exception e1) {
      try {
        myCountry = countryDao.findByLocalName(natianalityName);
      } catch (Exception e2) {
        throw new CountryNotFoundException("Country of nationality '" + natianalityName + "' not found.");
      }
    }

    return myCountry;
  }
}
