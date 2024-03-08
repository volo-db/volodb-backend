package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.SchoolEduDAO;
import dev.urner.volodb.entity.SchoolEdu;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolEduServiceImpl implements SchoolEduService {

  private final SchoolEduDAO schoolEduDAO;

  @Override
  public List<SchoolEdu> findAll() {
    return schoolEduDAO.findAll();
  }

  @Override
  public SchoolEdu findByName(String schoolEduName) {
    return schoolEduDAO.findByName(schoolEduName);
  }

  @Override
  public SchoolEdu findById(int schoolEduId) {
    return schoolEduDAO.findById(schoolEduId);
  }

}
