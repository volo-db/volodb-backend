package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocumentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VolunteerDocumentTypeDAOJpaImpl implements VolunteerDocumentTypeDAO {

  private final EntityManager entityManager;

  @Override
  public List<VolunteerDocumentType> findAll() {
    TypedQuery<VolunteerDocumentType> query = entityManager.createQuery("FROM VolunteerDocumentType",
        VolunteerDocumentType.class);
    return query.getResultList();
  }

  @Override
  public VolunteerDocumentType findById(int documentId) {
    return entityManager.find(VolunteerDocumentType.class, documentId);
  }

  @Override
  public VolunteerDocumentType save(VolunteerDocumentType documentType) {
    return entityManager.merge(documentType);
  }

  @Override
  public void deleteById(int documentId) {
    VolunteerDocumentType dbType = entityManager.find(VolunteerDocumentType.class, documentId);
    entityManager.remove(dbType);
  }

  @Override
  public VolunteerDocumentType findByName(String documentName) {
    TypedQuery<VolunteerDocumentType> query = entityManager
        .createQuery("SELECT vdt FROM VolunteerDocumentType vdt WHERE vdt.name = :dName", VolunteerDocumentType.class)
        .setParameter("dName", documentName);

    return query.getResultList().get(0);
  }

}
