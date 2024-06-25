package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocument;

@Repository
public interface VolunteerDocumentDAO extends ListCrudRepository<VolunteerDocument, Integer> {

  public VolunteerDocument findById(int id);

  public VolunteerDocument save(VolunteerDocument document);

  public void deleteById(int id);

  public List<VolunteerDocument> findAllByVolunteerId(int volunteerId, Sort sort);

  public List<VolunteerDocument> findByVolunteerIdAndNameContaining(int volunteerId, String name, Sort sort);

}
