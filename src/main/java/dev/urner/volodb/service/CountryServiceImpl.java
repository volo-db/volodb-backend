package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.CountryDAO;
import dev.urner.volodb.entity.Country;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryDAO countryDao;

  @Override
  public Country findByName(String countryName) {
    return countryDao.findByName(countryName);
  }
}
