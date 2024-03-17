package dev.urner.volodb.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import dev.urner.volodb.dao.VolunteerNoteDAO;
import dev.urner.volodb.entity.VolunteerNote;
import dev.urner.volodb.entity.VolunteerNoteInvalidFormatException;
import dev.urner.volodb.entity.VolunteerNoteNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerNoteServiceImpl implements VolunteerNoteService {

  private final VolunteerNoteDAO volunteerNoteDAO;

  @Override
  public List<VolunteerNote> findAllByVolunteerId(int volunteerId) {

    List<VolunteerNote> notes = volunteerNoteDAO.findAllByVolunteerId(volunteerId);

    if (notes == null)
      throw new VolunteerNoteNotFoundException("There are no Notes for Volunteer-Id: " + volunteerId);

    return notes;
  }

  @Override
  @Transactional
  public VolunteerNote save(VolunteerNote note, String username) {
    if (note.getUsername() != username)
      throw new VolunteerNoteInvalidFormatException("Not allowed to overwrite notes from other users.");

    return volunteerNoteDAO.save(note);
  }

  @Override
  @Transactional
  public void deleteById(int noteId, String username) {
    VolunteerNote dbNote = volunteerNoteDAO.findById(noteId);

    if (dbNote == null)
      throw new VolunteerNoteNotFoundException("VolunteerNote with Id '" + noteId + "' not found.");

    if (!dbNote.getUsername().equals(username))
      throw new VolunteerNoteInvalidFormatException("Not allowed to delete notes from other users.");

    volunteerNoteDAO.deleteById(noteId);
  }

  @Override
  @Transactional
  public VolunteerNote update(int noteId, Map<String, Object> fields) {

    VolunteerNote dbNote = volunteerNoteDAO.findById(noteId);

    if (dbNote == null)
      throw new VolunteerNoteNotFoundException("VolunteerNote with Id '" + noteId + "' not found.");

    fields.forEach((key, value) -> {

      // if User trys to set id-, timestamp-, user- of volunteer-field -> ignore
      if (key.toLowerCase().equals("id")
          || key.toLowerCase().equals("timestamp")
          || key.toLowerCase().equals("user")
          || key.toLowerCase().equals("volunteer"))
        return;

      if (key.toLowerCase().equals("type")) {
        dbNote.setType(value.toString());
        return;
      }

      if (key.toLowerCase().equals("note")) {
        dbNote.setNoteContent(value.toString());
        return;
      }
    });

    return volunteerNoteDAO.save(dbNote);
  }

}
