package dev.urner.volodb.service;

import java.util.List;

import dev.urner.volodb.entity.VocationalEdu;

public interface VocationalEduService {
  List<VocationalEdu> findAll();

  VocationalEdu findById(int vocationalEduId);

  VocationalEdu findByName(String vocationalEduName);

}
