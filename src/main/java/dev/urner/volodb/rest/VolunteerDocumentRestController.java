package dev.urner.volodb.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.VolunteerDocument;
import dev.urner.volodb.service.VolunteerDocumentService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class VolunteerDocumentRestController {

  private final VolunteerDocumentService volunteerDocumentService;

  @GetMapping("/{volunteerId}")
  public List<VolunteerDocument> getByVolunteerId(@PathVariable int volunteerId) {
    return volunteerDocumentService.findByVolunteerId(volunteerId);
  }

}
