package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerNote;

@Repository
public interface VolunteerNoteDAO extends ListCrudRepository<VolunteerNote, Integer> {

  public List<VolunteerNote> findAllByVolunteerId(int volunteerId, Sort sort);

  public List<VolunteerNote> findByVolunteerIdAndNoteContentContaining(
      int volunteerId,
      String noteContent,
      Sort sort);

  public VolunteerNote save(VolunteerNote note);

  public void deleteById(int id);

  public VolunteerNote findById(int id);

}
