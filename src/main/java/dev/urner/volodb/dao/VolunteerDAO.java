package dev.urner.volodb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;

import dev.urner.volodb.entity.Volunteer;

import java.util.List;

@Repository
public interface VolunteerDAO extends ListCrudRepository<Volunteer, Integer> {

  List<Volunteer> findAll();

  Volunteer findById(int id);

  Volunteer save(Volunteer volunteer);

  void deleteById(int id);
}
