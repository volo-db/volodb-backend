package dev.urner.volodb.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Project;

@Repository
public interface ProjectDAO extends PagingAndSortingRepository<Project, Integer> {

  public Project findById(int projectId);

  public Project save(Project theProject);

  public void deleteById(int theId);
}
