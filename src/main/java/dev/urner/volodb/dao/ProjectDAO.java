package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Project;

@Repository
public interface ProjectDAO extends ListCrudRepository<Project, Integer> {

  public List<Project> findAll();

  public Project findById(int projectId);

  public Project save(Project theProject);

  public void deleteById(int theId);
}
