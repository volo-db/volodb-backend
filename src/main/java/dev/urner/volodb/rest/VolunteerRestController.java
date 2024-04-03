package dev.urner.volodb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.urner.volodb.entity.Volunteer;
import dev.urner.volodb.entity.VolunteerInvalidFormatException;
import dev.urner.volodb.entity.VolunteerNotFoundException;
import dev.urner.volodb.service.VolunteerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/volunteers")
@RequiredArgsConstructor
public class VolunteerRestController {

  private final VolunteerService volunteerService;

  // expose "/volunteers" and return a list of volunteers
  @GetMapping("")
  public List<Volunteer> findAll() {
    return volunteerService.findAll();
  }

  // add mapping for GET /volunteers/{volunteerId}
  @GetMapping("/{volunteerId}")
  public Volunteer getVolunteer(@PathVariable int volunteerId) throws JsonMappingException, JsonProcessingException {

    Volunteer theVolunteer = volunteerService.findById(volunteerId);

    if (theVolunteer == null) {
      throw new VolunteerNotFoundException("Volunteer id not found - " + volunteerId);
    }

    return theVolunteer;
  }

  // add mapping for POST /volunteers - add new volunteer

  @PostMapping("")
  public Volunteer addVolunteer(@RequestBody Volunteer theVolunteer) {

    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update

    theVolunteer.setId(0);

    Volunteer dbVolunteer = volunteerService.save(theVolunteer);

    return dbVolunteer;
  }

  // add mapping for PUT /volunteers - update existing volunteer
  @PutMapping("")
  public Volunteer updateVolunteer(@RequestBody Volunteer theVolunteer) {
    theVolunteer.setId(0);

    Volunteer dbVolunteer = volunteerService.save(theVolunteer);

    return dbVolunteer;
  }

  @PatchMapping("/{volunteerId}")
  public Volunteer patchVolunteer(@RequestBody Map<String, Object> fields, @PathVariable int volunteerId) {
    return volunteerService.update(volunteerId, fields);
  }

  @DeleteMapping("/{volunteerId}")
  public String deleteVolunteer(@PathVariable int volunteerId) {

    Volunteer tempVolunteer = volunteerService.findById(volunteerId);

    // throw exception if null

    if (tempVolunteer == null) {
      throw new VolunteerNotFoundException("Volunteer id not found - " + volunteerId);
    }

    volunteerService.deleteById(volunteerId);

    return "Deleted volunteer id - " + volunteerId;
  }

  @PatchMapping("/{volunteerId}/avatar")
  public String setVolunteerAvatar(@PathVariable int volunteerId, @RequestParam MultipartFile avatar) {
    return volunteerService.SetAvatar(avatar, volunteerId);
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerErrorResponse> handleException(VolunteerNotFoundException exc) {
    VolunteerErrorResponse error = new VolunteerErrorResponse();

    HttpStatus status = HttpStatus.NOT_FOUND;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerErrorResponse> handleException(VolunteerInvalidFormatException exc) {
    VolunteerErrorResponse error = new VolunteerErrorResponse();

    HttpStatus status = HttpStatus.BAD_REQUEST;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerErrorResponse> handleException(RuntimeException exc) {
    VolunteerErrorResponse error = new VolunteerErrorResponse();

    HttpStatus status = HttpStatus.BAD_REQUEST;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }
}
