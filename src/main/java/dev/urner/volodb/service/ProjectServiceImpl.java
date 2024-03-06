package dev.urner.volodb.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import dev.urner.volodb.dao.ProjectDAO;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.entity.Project;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectDAO projectDAO;
  private final CountryService countryService;

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

  @Transactional
  @Override
  public Project update(int theProjectId, Map<String, Object> fields) {
    Project dbProject = projectDAO.findByProjectId(theProjectId);

    fields.forEach((key, value) -> {
      Field field = ReflectionUtils.findField(Project.class, key);
      field.setAccessible(true);

      // In Case of Field "country", use Country-Service to get a valid Country-Object
      if (field.getName() == "country") {
        Country projectCountry = countryService.findByName(value.toString());
        ReflectionUtils.setField(field, dbProject, projectCountry);
        return;
      }

      ReflectionUtils.setField(field, dbProject, value);
    });

    return projectDAO.save(dbProject);
  }

}
