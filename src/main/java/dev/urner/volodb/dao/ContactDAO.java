package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Contact;

@Repository
public interface ContactDAO extends ListCrudRepository<Contact, Integer> {

  public Contact findById(int id);

  public Contact save(Contact document);

  public void deleteById(int id);

  public List<Contact> findAllByPersonId(int personId);

}
