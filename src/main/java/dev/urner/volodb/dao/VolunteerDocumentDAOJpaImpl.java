package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocument;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VolunteerDocumentDAOJpaImpl implements VolunteerDocumentDAO {

  private final EntityManager entityManager;

  @Override
  public List<VolunteerDocument> findAll() {
    TypedQuery<VolunteerDocument> query = entityManager.createQuery("FROM VolunteerDocument", VolunteerDocument.class);
    return query.getResultList();
  }

  @Override
  public VolunteerDocument findById(int documentId) {
    return entityManager.find(VolunteerDocument.class, documentId);
  }

  @Override
  public VolunteerDocument save(VolunteerDocument document) {
    return entityManager.merge(document);
  }

  @Override
  public void deleteById(int documentId) {
    VolunteerDocument dbDocument = entityManager.find(VolunteerDocument.class, documentId);
    entityManager.remove(dbDocument);
  }

  @Override
  public List<VolunteerDocument> findByVolunteerId(int volunteerId) {
    TypedQuery<VolunteerDocument> query = entityManager
        .createQuery("SELECT vd FROM VolunteerDocument vd WHERE vd.volunteerId = :vId", VolunteerDocument.class)
        .setParameter("vId", volunteerId);

    return query.getResultList();
  }

}
