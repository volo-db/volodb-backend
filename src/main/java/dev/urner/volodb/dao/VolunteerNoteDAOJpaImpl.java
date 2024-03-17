package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerNote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VolunteerNoteDAOJpaImpl implements VolunteerNoteDAO {

  private final EntityManager entityManager;

  @Override
  public List<VolunteerNote> findAllByVolunteerId(int volunteerId) {
    TypedQuery<VolunteerNote> query = entityManager
        .createQuery("SELECT vn FROM VolunteerNote vn WHERE vn.volunteer = :vId", VolunteerNote.class)
        .setParameter("vId", volunteerId);

    return query.getResultList();
  }

  @Override
  public VolunteerNote save(VolunteerNote note) {
    return entityManager.merge(note);
  }

  @Override
  public void deleteById(int noteId) {
    VolunteerNote dbNote = entityManager.find(VolunteerNote.class, noteId);
    entityManager.remove(dbNote);
  }

  @Override
  public VolunteerNote findById(int noteId) {
    return entityManager.find(VolunteerNote.class, noteId);
  }

}
