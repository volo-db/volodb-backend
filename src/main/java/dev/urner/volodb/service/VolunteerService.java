package dev.urner.volodb.service;


import java.util.List;

import dev.urner.volodb.entity.Volunteer;

public interface VolunteerService {

    List<Volunteer> findAll();

    Volunteer findById(int theId);

    Volunteer save(Volunteer theVolunteer);

    void deleteById(int theId);

}
