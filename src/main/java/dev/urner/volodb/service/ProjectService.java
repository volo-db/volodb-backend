package dev.urner.volodb.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import dev.urner.volodb.dao.ProjectDAO;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.entity.CountryNotFoundException;
import dev.urner.volodb.entity.Project;
import dev.urner.volodb.entity.ProjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

  private final ProjectDAO projectDAO;
  private final CountryService countryService;

  public List<Project> findAll() {
    return projectDAO.findAll();
  }

  public Project findByProjectId(int theProjectId) {
    return projectDAO.findById(theProjectId);

  }

  @Transactional
  public Project save(Project theProject) {
    return projectDAO.save(theProject);
  }

  @Transactional
  public void deleteById(int theId) {
    projectDAO.deleteById(theId);
  }

  @Transactional
  public Project update(int theProjectId, Map<String, Object> fields) {
    Project dbProject = projectDAO.findById(theProjectId);

    if (dbProject == null)
      throw new ProjectNotFoundException("ProjectId " + theProjectId + " not found.");

    fields.forEach((key, value) -> {

      Field field = ReflectionUtils.findField(Project.class, key);

      // if fieldname does not exist in Object
      if (field == null)
        return;

      field.setAccessible(true);

      // if field "id" is set -> ignore
      if (field.getName() == "id")
        return;

      // In Case of Field "country", use Country-Service to get a valid Country-Object
      if (field.getName() == "country") {
        Country projectCountry;

        try {
          projectCountry = countryService.findByName(value.toString());
        } catch (Exception e) {
          throw new CountryNotFoundException("Country '" + value.toString()
              + "' not found. Countryname has to be given in the international english form e.g. 'Germany'");
        }

        ReflectionUtils.setField(field, dbProject, projectCountry);
        return;
      }

      ReflectionUtils.setField(field, dbProject, value);
    });

    return projectDAO.save(dbProject);
  }

}
