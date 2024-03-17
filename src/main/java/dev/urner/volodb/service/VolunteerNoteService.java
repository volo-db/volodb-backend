package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import dev.urner.volodb.entity.VolunteerNote;

public interface VolunteerNoteService {

  List<VolunteerNote> findAllByVolunteerId(int volunteerId);

  VolunteerNote save(VolunteerNote note, String username);

  VolunteerNote update(int noteId, Map<String, Object> fields);

  void deleteById(int noteId, String username);
}
