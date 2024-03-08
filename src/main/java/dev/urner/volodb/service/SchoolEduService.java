package dev.urner.volodb.service;

import java.util.List;

import dev.urner.volodb.entity.SchoolEdu;

public interface SchoolEduService {
  List<SchoolEdu> findAll();

  SchoolEdu findByName(String schoolEduName);

  SchoolEdu findById(int schoolEduId);
}
