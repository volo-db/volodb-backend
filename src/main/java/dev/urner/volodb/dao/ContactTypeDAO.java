package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.ContactType;

@Repository
public interface ContactTypeDAO extends ListCrudRepository<ContactType, Integer> {

  public List<ContactType> findAll();

  public ContactType findById(int id);

  public ContactType save(ContactType contactType);

  public void deleteById(int id);

  public ContactType findByName(String name);

}
