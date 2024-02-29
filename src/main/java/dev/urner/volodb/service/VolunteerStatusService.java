package dev.urner.volodb.service;

import dev.urner.volodb.entity.VolunteerStatus;

public interface VolunteerStatusService {

    VolunteerStatus findByName(String theName);
}
