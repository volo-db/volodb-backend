package dev.urner.volodb.service;

import java.util.Map;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerDocumentDAO;
import dev.urner.volodb.entity.VolunteerDocument;
import dev.urner.volodb.exception.DocumentNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerDocumentService {

  private final VolunteerDocumentDAO volunteerDocumentDAO;
  private final VolunteerDocumentTypeService documentTypeService;

  public List<VolunteerDocument> findAll() {
    return volunteerDocumentDAO.findAll();
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
    volunteerDocumentDAO.deleteById(documentId);
  }

  public List<VolunteerDocument> findAllByVolunteerId(int volunteerId, String sortField, boolean descending,
      String searchQuery) {

    Sort sort = Sort.by(descending ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
    List<VolunteerDocument> documents = volunteerDocumentDAO.findByVolunteerIdAndNameContaining(volunteerId,
        searchQuery, sort);

    if (documents == null)
      throw new DocumentNotFoundException(
          "There are no Documents with this combination of volunteerId and searchQuery");
    return documents;
  }

  public List<VolunteerDocument> findAllByVolunteerId(int volunteerId, String sortBy,
      boolean descending) {

    Sort sort = Sort.by(sortBy);

    if (descending)
      sort = sort.descending();

    return volunteerDocumentDAO.findAllByVolunteerId(volunteerId, sort);
  }

}
