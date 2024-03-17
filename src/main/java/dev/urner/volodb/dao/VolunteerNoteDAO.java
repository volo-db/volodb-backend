package dev.urner.volodb.dao;

import java.util.List;

import dev.urner.volodb.entity.VolunteerNote;

public interface VolunteerNoteDAO {

  List<VolunteerNote> findAllByVolunteerId(int volunteerId);

  VolunteerNote findById(int noteId);

  VolunteerNote save(VolunteerNote note);

  void deleteById(int noteId);
}
