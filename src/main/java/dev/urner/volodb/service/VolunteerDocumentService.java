package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import dev.urner.volodb.entity.VolunteerDocument;

public interface VolunteerDocumentService {

  List<VolunteerDocument> findAll();

  VolunteerDocument findById(int documentId);

  List<VolunteerDocument> findByVolunteerId(int volunteerId);

  VolunteerDocument save(VolunteerDocument document);

  VolunteerDocument update(int documentId, Map<String, Object> fields);

  void deleteById(int documentId);

}