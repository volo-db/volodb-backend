package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import dev.urner.volodb.entity.VolunteerDocumentType;

public interface VolunteerDocumentTypeService {

  List<VolunteerDocumentType> findAll();

  VolunteerDocumentType findById(int documentTypeId);

  VolunteerDocumentType findByName(String documentTypeName);

  VolunteerDocumentType save(VolunteerDocumentType documentType);

  VolunteerDocumentType update(int documentTypeId, Map<String, Object> fields);

  void deleteById(int documentTypeId);
}
