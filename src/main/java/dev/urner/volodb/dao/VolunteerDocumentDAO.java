package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.VolunteerDocument;

public interface VolunteerDocumentDAO {

  List<VolunteerDocument> findAll();

  VolunteerDocument findById(int documentId);

  List<VolunteerDocument> findByVolunteerId(int volunteerId);

  VolunteerDocument save(VolunteerDocument document);

  void deleteById(int documentId);
}
