package dev.urner.volodb.dao;

import dev.urner.volodb.entity.VolunteerStatus;

public interface VolunteerStatusDAO {

  VolunteerStatus findByName(String theName);
}










