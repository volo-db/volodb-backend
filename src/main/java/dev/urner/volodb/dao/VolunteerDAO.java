package dev.urner.volodb.dao;


import java.util.List;

import dev.urner.volodb.entity.Volunteer;

public interface VolunteerDAO {

    List<Volunteer> findAll();

    Volunteer findById(int theId);

    Volunteer save(Volunteer theVolunteer);

    void deleteById(int theId);
}










