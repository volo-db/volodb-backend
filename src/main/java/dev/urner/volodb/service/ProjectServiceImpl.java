package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ProjectDAO;
import dev.urner.volodb.entity.Project;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectDAO projectDAO;

  @Override
  public List<Project> findAll() {

    return projectDAO.findAll();
  }

  @Override
  public Project findByProjectId(int theProjectId) {
    return projectDAO.findByProjectId(theProjectId);
    
  }

  @Transactional
  @Override
  public Project save(Project theProject) {
    return projectDAO.save(theProject);
  }

  @Transactional
  @Override
  public void deleteById(int theId) {
    projectDAO.deleteById(theId);
  }

  
}
