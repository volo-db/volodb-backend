package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerStatusDAO;
import dev.urner.volodb.entity.VolunteerStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerStatusService {

  private final VolunteerStatusDAO volunteerStatusDAO;

  public VolunteerStatus findByName(String theName) {
    return volunteerStatusDAO.findByName(theName);
  }

}
