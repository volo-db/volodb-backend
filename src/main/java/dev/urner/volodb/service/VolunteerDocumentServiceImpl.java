package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerDocumentDAO;
import dev.urner.volodb.entity.VolunteerDocument;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerDocumentServiceImpl implements VolunteerDocumentService {

  private final VolunteerDocumentDAO volunteerDocumentDAO;
  private final VolunteerDocumentTypeService documentTypeService;

  @Override
  public List<VolunteerDocument> findAll() {
    return volunteerDocumentDAO.findAll();
  }

  @Override
  public VolunteerDocument findById(int documentId) {
    return volunteerDocumentDAO.findById(documentId);
  }

  @Override
  @Transactional
  public VolunteerDocument save(VolunteerDocument document) {
    return volunteerDocumentDAO.save(document);
  }

  @Override
  @Transactional
  public VolunteerDocument update(int documentId, Map<String, Object> fields) {
    VolunteerDocument dbDocument = volunteerDocumentDAO.findById(documentId);

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("type")) {
        dbDocument.setDocumentType(documentTypeService.findByName(value.toString()));
        return;
      }
    });

    return dbDocument;
  }

  @Override
  @Transactional
  public void deleteById(int documentId) {
    documentTypeService.deleteById(documentId);
  }

  @Override
  public List<VolunteerDocument> findByVolunteerId(int volunteerId) {
    return volunteerDocumentDAO.findByVolunteerId(volunteerId);
  }

}
