package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VocationalEduDAO;
import dev.urner.volodb.entity.VocationalEdu;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VocationalEduService {

  private final VocationalEduDAO vocationalEduDAO;

  public List<VocationalEdu> findAll() {
    return vocationalEduDAO.findAll();
  }

  public VocationalEdu findById(int vocationalEduId) {
    return vocationalEduDAO.findById(vocationalEduId);
  }

  public VocationalEdu findByName(String vocationalEduName) {
    return vocationalEduDAO.findByName(vocationalEduName);
  }

}
