package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerDocumentTypeDAO;
import dev.urner.volodb.entity.VolunteerDocumentType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerDocumentTypeService {

  private final VolunteerDocumentTypeDAO volunteerDocumentTypeDAO;

  public List<VolunteerDocumentType> findAll() {
    return volunteerDocumentTypeDAO.findAll();
  }

  public VolunteerDocumentType findById(int documentTypeId) {
    return volunteerDocumentTypeDAO.findById(documentTypeId);
  }

  @Transactional
  public VolunteerDocumentType save(VolunteerDocumentType documentType) {
    return volunteerDocumentTypeDAO.save(documentType);
  }

  @Transactional
  public void deleteById(int documentTypeId) {
    volunteerDocumentTypeDAO.deleteById(documentTypeId);
  }

  @Transactional
  public VolunteerDocumentType update(int documentTypeId, Map<String, Object> fields) {
    VolunteerDocumentType dbDocumentType = volunteerDocumentTypeDAO.findById(documentTypeId);

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("name")) {
        dbDocumentType.setName(value.toString());
        return;
      }
      if (key.toLowerCase().equals("description")) {
        dbDocumentType.setDescription(value.toString());
        return;
      }
    });

    return volunteerDocumentTypeDAO.save(dbDocumentType);
  }

  public VolunteerDocumentType findByName(String documentTypeName) {
    return volunteerDocumentTypeDAO.findByName(documentTypeName);
  }

}
