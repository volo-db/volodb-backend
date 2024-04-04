package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerDocumentDAO;
import dev.urner.volodb.entity.VolunteerDocument;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerDocumentService {

  private final VolunteerDocumentDAO volunteerDocumentDAO;
  private final VolunteerDocumentTypeService documentTypeService;

  public Page<VolunteerDocument> findAll(int page, int pageSize) {
    return volunteerDocumentDAO.findAll(PageRequest.of(page, pageSize));
  }

  public VolunteerDocument findById(int documentId) {
    return volunteerDocumentDAO.findById(documentId);
  }

  @Transactional
  public VolunteerDocument save(VolunteerDocument document) {
    return volunteerDocumentDAO.save(document);
  }

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

  @Transactional
  public void deleteById(int documentId) {
    documentTypeService.deleteById(documentId);
  }

  public Page<VolunteerDocument> findAllByVolunteerId(int volunteerId, int page, int pageSize) {
    return volunteerDocumentDAO.findAllByVolunteerId(volunteerId, PageRequest.of(page, pageSize));
  }

}
