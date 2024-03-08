package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.VocationalEdu;

public interface VocationalEduDAO {
  List<VocationalEdu> findAll();

  VocationalEdu findById(int vocationalEduId);

  VocationalEdu findByName(String vocationalEduName);
}
