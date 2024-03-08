package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ReligionDAO;
import dev.urner.volodb.entity.Religion;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReligionServiceImpl implements ReligionService {

  private final ReligionDAO religionDAO;

  @Override
  public List<Religion> findAll() {
    return religionDAO.findAll();
  }

  @Override
  public Religion findByName(String religionName) {
    return religionDAO.findByName(religionName);
  }

  @Override
  public Religion findByShorthand(String religionShorthand) {
    return religionDAO.findByShorthand(religionShorthand);
  }

}
