package dev.urner.volodb.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.ContactType;
import dev.urner.volodb.service.ContactTypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/contacts/types")
@RequiredArgsConstructor
public class ContactTypeRestController {

  private final ContactTypeService contactTypeService;

  @GetMapping
  public List<ContactType> getAll() {
    return contactTypeService.findAll();
  }

  // @GetMapping("/{documentTypeId}")
  // public VolunteerDocumentType getById(@PathVariable int documentTypeId) {
  // return volunteerDocumentTypeService.findById(documentTypeId);
  // }

  // @PostMapping
  // public VolunteerDocumentType postNewVolunteerDocumentType(@RequestBody
  // VolunteerDocumentType documentType) {
  // return volunteerDocumentTypeService.save(documentType);
  // }

  // @PatchMapping("/{documentTypeId}")
  // public VolunteerDocumentType patchById(@PathVariable int documentTypeId,
  // @RequestBody Map<String, Object> fields) {
  // return volunteerDocumentTypeService.update(documentTypeId, fields);
  // }

  // @DeleteMapping("/{documentTypeId}")
  // public String deleteById(@PathVariable int documentTypeId) {
  // volunteerDocumentTypeService.deleteById(documentTypeId);
  // return "DocumentType with Id '" + documentTypeId + "' deleted.";
  // }

}
