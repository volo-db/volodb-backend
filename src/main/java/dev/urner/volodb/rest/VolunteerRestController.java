package dev.urner.volodb.rest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.urner.volodb.entity.Contact;
import dev.urner.volodb.entity.Volunteer;
import dev.urner.volodb.entity.VolunteerDocument;
import dev.urner.volodb.entity.VolunteerInvalidFormatException;
import dev.urner.volodb.entity.VolunteerNotFoundException;
import dev.urner.volodb.entity.VolunteerNote;
import dev.urner.volodb.security.UserPrincipal;
import dev.urner.volodb.service.ContactService;
import dev.urner.volodb.service.VolunteerDocumentService;
import dev.urner.volodb.service.VolunteerNoteService;
import dev.urner.volodb.service.VolunteerService;
import lombok.RequiredArgsConstructor;

import java.lang.System;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/volunteers")
@RequiredArgsConstructor
public class VolunteerRestController {

  private final VolunteerService volunteerService;
  private final VolunteerNoteService volunteerNoteService;
  private final VolunteerDocumentService volunteerDocumentService;
  private final ContactService contactService;

  // expose "/volunteers" and return a list of volunteers
  @GetMapping
  public Page<Volunteer> findAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
      @RequestParam(name = "sortField", defaultValue = "") String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {

    // unsorted
    if (sortField.equals(""))
      return volunteerService.findAll(page, pageSize);

    // sorted
    if (sortOrder.equals("asc"))
      return volunteerService.findAll(page, pageSize, sortField, false);
    if (sortOrder.equals("desc"))
      return volunteerService.findAll(page, pageSize, sortField, true);

    throw new VolunteerInvalidFormatException("SortOrder '" + sortOrder + "' not supported.");
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

  @PostMapping
  public Volunteer addVolunteer(@RequestBody Volunteer theVolunteer) {

    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update

    theVolunteer.setId(0);

    Volunteer dbVolunteer = volunteerService.save(theVolunteer);

    return dbVolunteer;
  }

  // add mapping for PUT /volunteers - update existing volunteer
  @PutMapping
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

  // Avatar

  @PatchMapping("/{volunteerId}/avatar")
  public String setVolunteerAvatar(@PathVariable int volunteerId, @RequestParam MultipartFile avatar) {
    return volunteerService.SetAvatar(avatar, volunteerId);
  }

  // **********************************
  // Notes
  // **********************************

  // GET all notes BY VoloID
  @GetMapping("/{volunteerId}/notes")
  public Page<VolunteerNote> getAllNotesFromVolo(@PathVariable int volunteerId,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
      @RequestParam(name = "sortField", defaultValue = "") String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {
    if (sortField.equals(""))
      return volunteerNoteService.findAllByVolunteerId(volunteerId, page, pageSize);

    if (sortOrder.equals("asc"))
      return volunteerNoteService.findAllByVolunteerId(volunteerId, page, pageSize, sortField, false);
    if (sortOrder.equals("desc"))
      return volunteerNoteService.findAllByVolunteerId(volunteerId, page, pageSize, sortField, true);

    throw new VolunteerInvalidFormatException("SortOrder '" + sortOrder + "' not supported.");

  }

  // POST note BY VoloID
  @PostMapping("/{volunteerId}/notes")
  public VolunteerNote postNewNote(@PathVariable int volunteerId, @RequestBody VolunteerNote note,
      @AuthenticationPrincipal UserPrincipal principal) {
    note.setVolunteerId(volunteerId);
    note.setTimestamp(LocalDateTime.now());
    note.setUsername(principal.getUsername());
    return volunteerNoteService.save(note, principal.getUsername());
  }

  // PATCH
  @PatchMapping("/{volunteerId}/notes/{noteId}")
  public VolunteerNote updateVolunteerNote(@RequestBody Map<String, Object> fields, @PathVariable int volunteerId,
      @PathVariable int noteId) {
    return volunteerNoteService.update(noteId, fields);
  }

  // DELTE BY NoteID
  @DeleteMapping("/{volunteerId}/notes/{noteId}")
  public String deleteByNoteId(@PathVariable int volunteerId, @PathVariable int noteId,
      @AuthenticationPrincipal UserPrincipal principal) {
    volunteerNoteService.deleteById(noteId, principal.getUsername());
    return "Volunteer-Note with Id '" + noteId + "' deleted.";
  }

  // **********************************
  // Documents
  // **********************************

  @GetMapping("/{volunteerId}/documents")
  public Page<VolunteerDocument> getAllDocuments(@PathVariable int volunteerId,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
      @RequestParam(name = "sortField", defaultValue = "") String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder) {

    if (sortField.equals(""))
      return volunteerDocumentService.findAllByVolunteerId(volunteerId, page, pageSize);

    if (sortOrder.equals("asc"))
      return volunteerDocumentService.findAllByVolunteerId(volunteerId, page, pageSize, sortField, false);
    if (sortOrder.equals("desc"))
      return volunteerDocumentService.findAllByVolunteerId(volunteerId, page, pageSize, sortField, true);

    throw new VolunteerInvalidFormatException("SortOrder '" + sortOrder + "' not supported.");

  }

  @PostMapping("/{volunteerId}/documents")
  public VolunteerDocument postNewDocument(
      @PathVariable int volunteerId,
      @RequestParam MultipartFile document,
      @RequestParam int documentTypeId,
      @AuthenticationPrincipal UserPrincipal principal) {
    return volunteerService.saveDocument(document, documentTypeId, volunteerId, principal.getUsername());
  }

  // **********************************
  // Exceptionhandling:
  // **********************************

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(VolunteerNotFoundException exc) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(VolunteerInvalidFormatException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(RuntimeException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

  // **********************************
  // Contacts
  // **********************************

  // GET all contacts BY VoloID
  @GetMapping("/{volunteerId}/contacts")
  public List<Contact> getAllContactsFromVolo(@PathVariable int volunteerId) {

    return contactService.findAllByVolunteerId(volunteerId);
  }

  @GetMapping("/{volunteerId}/contacts/{contactId}")
  public Contact getContactById(@PathVariable int volunteerId, @PathVariable int contactId) {
    return contactService.findById(contactId);
  }

  // POST contact BY VoloID
  @PostMapping("/{volunteerId}/contacts")
  public Contact postNewContact(@PathVariable int volunteerId, @RequestBody Map<String, Object> fields) {
    return contactService.save(volunteerId, fields);
  }

  // PATCH contact BY ContactID
  @PatchMapping("/{volunteerId}/contacts/{contactId}")
  public Contact updateVolunteerContact(@RequestBody Map<String, Object> fields,
      @PathVariable int volunteerId,
      @PathVariable int contactId) {
    return contactService.update(contactId, fields);
  }

  // DELTE BY ContactID
  @DeleteMapping("/{volunteerId}/contacts/{contactId}")
  public String deleteByContactId(@PathVariable int volunteerId, @PathVariable int contactId) {
    contactService.deleteById(contactId);
    return "Contact with Id '" + contactId + "' deleted.";
  }

}
