package dev.urner.volodb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.urner.volodb.entity.CountryNotFoundException;
import dev.urner.volodb.entity.Project;
import dev.urner.volodb.entity.ProjectInvalidFormatException;
import dev.urner.volodb.entity.ProjectNotFoundException;
import dev.urner.volodb.service.ProjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectRestController {

  private final ProjectService projectService;

  // expose "/project" and return a list of volunteers
  @GetMapping("")
  public List<Project> findAll() {
    return projectService.findAll();
  }

  @GetMapping("/{projectId}")
  public Project getProjectById(@PathVariable int projectId) {

    Project theProject = projectService.findByProjectId(projectId);

    if (theProject == null) {
      throw new ProjectNotFoundException("Project id not found - " + projectId);
    }

    return theProject;
  }

  @PostMapping("")
  public Project postProject(@RequestBody Project newProject) {
    newProject.setId(0);

    return projectService.save(newProject);
  }

  // ---> NOT WORKING... allways new Project
  @PutMapping("/{projectId}")
  public Project putProjectById(@RequestBody Project theProject, @PathVariable int projectId) {
    theProject.setId(projectId);
    return projectService.save(theProject);
  }

  // PATCH-Mapping
  @PatchMapping("/{projectId}")
  public Project patchProjectById(@RequestBody Map<String, Object> fields, @PathVariable int projectId) {
    return projectService.update(projectId, fields);
  }

  @DeleteMapping("/{projectId}")
  public String deleteProjectById(@PathVariable int projectId) {
    Project tempProject = projectService.findByProjectId(projectId);
    if (tempProject == null) {
      // THROW NEW PROJECT NOT FOUND EXCEPTION
      throw new ProjectNotFoundException("Project-Id " + projectId + " not found.");
    }

    projectService.deleteById(projectId);
    return "✅ Deleted project id - " + projectId;
  }

  // Exception-Hanlder:

  @ExceptionHandler
  public ResponseEntity<ProjectErrorResponse> handleException(ProjectNotFoundException exc) {
    ProjectErrorResponse error = new ProjectErrorResponse();

    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    error.setStatus(httpStatus.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler
  public ResponseEntity<ProjectErrorResponse> handleException(ProjectInvalidFormatException exc) {
    ProjectErrorResponse error = new ProjectErrorResponse();

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    error.setStatus(httpStatus.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler
  public ResponseEntity<ProjectErrorResponse> handleException(CountryNotFoundException exc) {
    ProjectErrorResponse error = new ProjectErrorResponse();

    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    error.setStatus(httpStatus.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler
  public ResponseEntity<ProjectErrorResponse> handleException(RuntimeException exc) {
    ProjectErrorResponse error = new ProjectErrorResponse();

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    error.setStatus(httpStatus.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }
}
