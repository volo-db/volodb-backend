package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Address;

@Repository
public interface AddressDAO extends ListCrudRepository<Address, Integer> {

  public Address findById(int id);

  public Address save(Address document);

  public void deleteById(int id);

  public List<Address> findAllByPersonId(int personId);

}
