package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocument;

@Repository
public interface VolunteerDocumentDAO extends ListCrudRepository<VolunteerDocument, Integer> {

  public List<VolunteerDocument> findAll();

  public VolunteerDocument findById(int id);

  public VolunteerDocument save(VolunteerDocument document);

  public void deleteById(int id);

  public List<VolunteerDocument> findByVolunteerId(int volunteerId);

}
