package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.Project;

public interface ProjectDAO {
    
  List<Project> findAll();

  Project findByProjectId(int projectId);

  Project save(Project theProject);

  void deleteById(int theId);
}
