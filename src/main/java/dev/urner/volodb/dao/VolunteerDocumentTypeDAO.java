package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.VolunteerDocumentType;

public interface VolunteerDocumentTypeDAO {

  List<VolunteerDocumentType> findAll();

  VolunteerDocumentType findById(int documentId);

  VolunteerDocumentType findByName(String documentName);

  VolunteerDocumentType save(VolunteerDocumentType documentType);

  void deleteById(int documentId);
}
