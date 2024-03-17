package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.VolunteerNote;
import dev.urner.volodb.entity.VolunteerNoteNotFoundException;
import dev.urner.volodb.security.UserPrincipal;
import dev.urner.volodb.service.VolunteerNoteService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class VolunteerNoteRestController {

  private final VolunteerNoteService volunteerNoteService;

  // GET BY VoloID
  @GetMapping("/{volunteerId}")
  public List<VolunteerNote> getAllByVolunteerId(@PathVariable int volunteerId) {
    return volunteerNoteService.findAllByVolunteerId(volunteerId);
  }

  // POST
  @PostMapping("")
  public VolunteerNote postNewNote(@RequestBody VolunteerNote note, @AuthenticationPrincipal UserPrincipal principal) {
    note.setTimestamp(LocalDateTime.now());
    note.setUsername(principal.getUsername());
    return volunteerNoteService.save(note, principal.getUsername());
  }

  // PATCH
  @PatchMapping("/{noteId}")
  public VolunteerNote updateVolunteerNote(@RequestBody Map<String, Object> fields, @PathVariable int noteId) {
    return volunteerNoteService.update(noteId, fields);
  }

  // DELTE BY NoteID
  @DeleteMapping("/{noteId}")
  public String deleteByNoteId(@PathVariable int noteId, @AuthenticationPrincipal UserPrincipal principal) {
    volunteerNoteService.deleteById(noteId, principal.getUsername());
    return "Volunteer-Note with Id '" + noteId + "' deleted.";
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerNoteErrorResponse> handleException(VolunteerNoteNotFoundException exc) {
    VolunteerNoteErrorResponse error = new VolunteerNoteErrorResponse();
    HttpStatus status = HttpStatus.NOT_FOUND;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerNoteErrorResponse> handleException(RuntimeException exc) {
    VolunteerNoteErrorResponse error = new VolunteerNoteErrorResponse();

    HttpStatus status = HttpStatus.BAD_REQUEST;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }
}
