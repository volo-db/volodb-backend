package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VocationalEduDAO;
import dev.urner.volodb.entity.VocationalEdu;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VocationalEduServiceImpl implements VocationalEduService {

  private final VocationalEduDAO vocationalEduDAO;

  @Override
  public List<VocationalEdu> findAll() {
    return vocationalEduDAO.findAll();
  }

  @Override
  public VocationalEdu findById(int vocationalEduId) {
    return vocationalEduDAO.findById(vocationalEduId);
  }

  @Override
  public VocationalEdu findByName(String vocationalEduName) {
    return vocationalEduDAO.findByName(vocationalEduName);
  }

}
