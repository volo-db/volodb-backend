package dev.urner.volodb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.urner.volodb.entity.Project;
import dev.urner.volodb.entity.ProjectInvalidFormatException;
import dev.urner.volodb.entity.ProjectNotFoundException;
import dev.urner.volodb.service.ProjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRestController {

  private final ProjectService projectService;

  // expose "/project" and return a list of volunteers
  @GetMapping("/project")
  public List<Project> findAll() {
    return projectService.findAll();
  }

  @GetMapping("/project/{projectId}")
  public Project getMethodName(@PathVariable int projectId) {

    Project theProject = projectService.findByProjectId(projectId);

    if (theProject == null) {
      throw new ProjectNotFoundException("Project id not found - " + projectId);
    }

    return theProject;
  }

  @PostMapping("/project")
  public Project postMethodName(@RequestBody Project newProject) {
    newProject.setId(0);

    return projectService.save(newProject);
  }

  // ---> NOT WORKING... allways new Project
  @PutMapping("/project/{projectId}")
  public Project putMethodName(@RequestBody Project theProject, @PathVariable int projectId) {
    theProject.setId(projectId);
    return projectService.save(theProject);
  }

  // PATCH-Mapping

  @DeleteMapping("/project/{projectId}")
  public String deleteProject(@PathVariable int projectId) {
    Project tempProject = projectService.findByProjectId(projectId);
    if (tempProject == null) {
      // THROW NEW PROJECT NOT FOUND EXCEPTION
    }

    projectService.deleteById(projectId);
    return "Deleted project id - " + projectId;
  }

  // @DeleteMapping("/project/{volunteerId}")
  // public String deleteVolunteer(@PathVariable int volunteerId) {

  // Volunteer tempVolunteer = volunteerService.findById(volunteerId);

  // // throw exception if null

  // if (tempVolunteer == null) {
  // throw new VolunteerNotFoundException("Volunteer id not found - " +
  // volunteerId);
  // }

  // volunteerService.deleteById(volunteerId);

  // return "Deleted volunteer id - " + volunteerId;
  // }

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
  public ResponseEntity<ProjectErrorResponse> handleException(RuntimeException exc) {
    ProjectErrorResponse error = new ProjectErrorResponse();

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    error.setStatus(httpStatus.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }
}
