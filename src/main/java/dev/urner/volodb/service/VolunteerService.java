package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import dev.urner.volodb.entity.Volunteer;

public interface VolunteerService {

  List<Volunteer> findAll();

  Volunteer findById(int theId);

  Volunteer save(Volunteer theVolunteer);

  Volunteer update(int theVolunteerId, Map<String, Object> fields);

  void deleteById(int theId);

}
