package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.CountryDAO;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.entity.CountryNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryDAO countryDao;

  @Override
  public Country findByName(String countryName) {
    return countryDao.findByName(countryName);
  }

  @Override
  public Country findByNationalityName(String natianalityName) {
    Country myCountry = null;
    try {
      myCountry = countryDao.findByNationalityName(natianalityName);
    } catch (Exception e1) {
      try {
        myCountry = countryDao.findByName(natianalityName);
      } catch (Exception e2) {
        throw new CountryNotFoundException("Country of nationality '" + natianalityName + "' not found.");
      }
    }

    return myCountry;
  }
}
