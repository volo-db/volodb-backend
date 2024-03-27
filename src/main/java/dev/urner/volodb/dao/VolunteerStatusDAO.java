package dev.urner.volodb.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerStatus;

@Repository
public interface VolunteerStatusDAO extends ListCrudRepository<VolunteerStatus, Integer> {

  public VolunteerStatus findByName(String name);

}
