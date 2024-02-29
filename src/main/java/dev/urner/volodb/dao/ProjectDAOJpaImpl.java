package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectDAOJpaImpl implements ProjectDAO {
  private final EntityManager entityManager;

  @Override
  public List<Project> findAll() {

    TypedQuery<Project> theQuery = entityManager.createQuery("from Project", Project.class);

    List<Project> projects = theQuery.getResultList();

    return projects;
  }

  @Override
  public Project findByProjectId(int projectId) {
    return entityManager.find(Project.class, projectId);
  }

  @Override
  public Project save(Project theProject) {
    Project dbProject = entityManager.merge(theProject);
    return dbProject;
  }

  @Override
  public void deleteById(int theId) {
    Project theProject = entityManager.find(Project.class, theId);

    entityManager.remove(theProject);
  }
}
