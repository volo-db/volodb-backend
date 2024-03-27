package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.SchoolEduDAO;
import dev.urner.volodb.entity.SchoolEdu;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolEduService {

  private final SchoolEduDAO schoolEduDAO;

  public List<SchoolEdu> findAll() {
    return schoolEduDAO.findAll();
  }

  public SchoolEdu findByName(String schoolEduName) {
    return schoolEduDAO.findByName(schoolEduName);
  }

  public SchoolEdu findById(int schoolEduId) {
    return schoolEduDAO.findById(schoolEduId);
  }

}
