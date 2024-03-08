package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.Religion;

public interface ReligionDAO {
  List<Religion> findAll();

  Religion findByName(String religionName);

  Religion findByShorthand(String religionShorthand);
}
