package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import dev.urner.volodb.entity.Project;

public interface ProjectService {
  List<Project> findAll();

  Project findByProjectId(int theProjectId);

  Project save(Project theProject);

  Project update(int theProjectId, Map<String, Object> fields);

  void deleteById(int theId);
}
