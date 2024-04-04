package dev.urner.volodb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerNote;

@Repository
public interface VolunteerNoteDAO extends PagingAndSortingRepository<VolunteerNote, Integer> {

  public Page<VolunteerNote> findAllByVolunteerId(int volunteerId, Pageable pageable);

  public VolunteerNote save(VolunteerNote note);

  public void deleteById(int id);

  public VolunteerNote findById(int id);

}
