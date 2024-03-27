package dev.urner.volodb.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.VolunteerDocumentType;
import dev.urner.volodb.service.VolunteerDocumentTypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/documents/types")
@RequiredArgsConstructor
public class VolunteerDocumentTypeRestController {

  private final VolunteerDocumentTypeService volunteerDocumentTypeService;

  @GetMapping("")
  public List<VolunteerDocumentType> getAll() {
    return volunteerDocumentTypeService.findAll();
  }

  @GetMapping("/{documentTypeId}")
  public VolunteerDocumentType getById(@PathVariable int documentTypeId) {
    return volunteerDocumentTypeService.findById(documentTypeId);
  }

  @PostMapping("")
  public VolunteerDocumentType postNewVolunteerDocumentType(@RequestBody VolunteerDocumentType documentType) {
    return volunteerDocumentTypeService.save(documentType);
  }

  @PatchMapping("/{documentTypeId}")
  public VolunteerDocumentType patchById(@PathVariable int documentTypeId, @RequestBody Map<String, Object> fields) {
    return volunteerDocumentTypeService.update(documentTypeId, fields);
  }

  @DeleteMapping("/{documentTypeId}")
  public String deleteById(@PathVariable int documentTypeId) {
    volunteerDocumentTypeService.deleteById(documentTypeId);
    return "DocumentType with Id '" + documentTypeId + "' deleted.";
  }

}
