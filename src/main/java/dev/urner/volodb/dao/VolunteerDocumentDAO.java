package dev.urner.volodb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocument;

@Repository
public interface VolunteerDocumentDAO extends PagingAndSortingRepository<VolunteerDocument, Integer> {

  public VolunteerDocument findById(int id);

  public VolunteerDocument save(VolunteerDocument document);

  public void deleteById(int id);

  public Page<VolunteerDocument> findAllByVolunteerId(int volunteerId, Pageable pageable);

}
