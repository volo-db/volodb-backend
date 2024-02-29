package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CountryDAOJpaImpl implements CountryDAO {

  private final EntityManager entityManager;

  @Override
  public Country findByName(String countryName) {
    TypedQuery<Country> query = entityManager
        .createQuery("SELECT c FROM Country c WHERE c.localName = :cName", Country.class)
        .setParameter("cName", countryName);

    List<Country> countrys = query.getResultList();

    return countrys.get(0);
  }

}
