package dev.urner.volodb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.urner.volodb.entity.Volunteer;
import dev.urner.volodb.entity.VolunteerNotFoundException;
import dev.urner.volodb.service.VolunteerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VolunteerRestController {

    private final VolunteerService volunteerService;

    @GetMapping("/")
    public String getTest() {
        return "hello now!";
    }

    

    // expose "/volunteers" and return a list of volunteers
    @GetMapping("/volunteers")
    public List<Volunteer> findAll() {
        return volunteerService.findAll();
    }

    // add mapping for GET /volunteers/{volunteerId}

    @GetMapping("/volunteers/{volunteerId}")
    public Volunteer getVolunteer(@PathVariable int volunteerId) throws JsonMappingException, JsonProcessingException {

        Volunteer theVolunteer = volunteerService.findById(volunteerId);

        if (theVolunteer == null) {
            throw new VolunteerNotFoundException("Volunteer id not found - " + volunteerId);
        }

        
        return theVolunteer;
    }

    // add mapping for POST /volunteers - add new volunteer

    @PostMapping("/volunteers")
    public Volunteer addVolunteer(@RequestBody Volunteer theVolunteer) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theVolunteer.setId(0);

        Volunteer dbVolunteer = volunteerService.save(theVolunteer);

        return dbVolunteer;
    }

    // add mapping for PUT /volunteers - update existing volunteer

    @PutMapping("/volunteers")
    public Volunteer updateVolunteer(@RequestBody Volunteer theVolunteer) {

        Volunteer dbVolunteer = volunteerService.save(theVolunteer);

        return dbVolunteer;
    }

    // add mapping for DELETE /volunteers/{volunteerId} - delete volunteer

    @DeleteMapping("/volunteers/{volunteerId}")
    public String deleteVolunteer(@PathVariable int volunteerId) {

        Volunteer tempVolunteer = volunteerService.findById(volunteerId);

        // throw exception if null

        if (tempVolunteer == null) {
            throw new VolunteerNotFoundException("Volunteer id not found - " + volunteerId);
        }
        
        volunteerService.deleteById(volunteerId);
        

        return "Deleted volunteer id - " + volunteerId;
    }

    @ExceptionHandler
    public ResponseEntity<VolunteerErrorResponse> handleException(VolunteerNotFoundException exc){
      VolunteerErrorResponse error = new VolunteerErrorResponse();

      error.setStatus(HttpStatus.NOT_FOUND.value());
      error.setMessage(exc.getMessage());
      error.setTimeStamp(System.currentTimeMillis());

      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<VolunteerErrorResponse> handleException(RuntimeException exc){
      VolunteerErrorResponse error = new VolunteerErrorResponse();

      error.setStatus(HttpStatus.BAD_REQUEST.value());
      error.setMessage(exc.getMessage());
      error.setTimeStamp(System.currentTimeMillis());

      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}














