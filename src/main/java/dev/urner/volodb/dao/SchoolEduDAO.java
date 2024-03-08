package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.SchoolEdu;

public interface SchoolEduDAO {
  List<SchoolEdu> findAll();

  SchoolEdu findById(int schoolEduId);

  SchoolEdu findByName(String schoolEduName);
}
