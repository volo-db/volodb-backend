package dev.urner.volodb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.urner.volodb.entity.Project;
import dev.urner.volodb.entity.Volunteer;
import dev.urner.volodb.entity.VolunteerNotFoundException;
import dev.urner.volodb.service.ProjectService;
import dev.urner.volodb.service.VolunteerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    return projectService.findByProjectId(projectId);
  }

  @PostMapping("/project")
  public Project postMethodName(@RequestBody Project newProject) {
    return projectService.save(newProject);
  }

  // ---> NOT WORKING... allways new Project
  // @PutMapping("/project")
  // public Project putMethodName(@RequestBody Project theProject) {
  // return projectService.save(theProject);
  // }

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

  // @ExceptionHandler
  // public ResponseEntity<VolunteerErrorResponse>
  // handleException(VolunteerNotFoundException exc){
  // VolunteerErrorResponse error = new VolunteerErrorResponse();

  // error.setStatus(HttpStatus.NOT_FOUND.value());
  // error.setMessage(exc.getMessage());
  // error.setTimeStamp(System.currentTimeMillis());

  // return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  // }

  // @ExceptionHandler
  // public ResponseEntity<VolunteerErrorResponse>
  // handleException(RuntimeException exc){
  // VolunteerErrorResponse error = new VolunteerErrorResponse();

  // error.setStatus(HttpStatus.BAD_REQUEST.value());
  // error.setMessage(exc.getMessage());
  // error.setTimeStamp(System.currentTimeMillis());

  // return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  // }
}
