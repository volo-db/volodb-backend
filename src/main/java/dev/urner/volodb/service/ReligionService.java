package dev.urner.volodb.service;

import java.util.List;

import dev.urner.volodb.entity.Religion;

public interface ReligionService {
  List<Religion> findAll();

  Religion findByName(String religionName);

  Religion findByShorthand(String religionShorthand);
}
