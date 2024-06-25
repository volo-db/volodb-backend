package dev.urner.volodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.VolunteerNoteDAO;
import dev.urner.volodb.entity.VolunteerNote;
import dev.urner.volodb.exception.NoteNotFoundException;
import dev.urner.volodb.exception.VolunteerNoteInvalidFormatException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolunteerNoteService {

  private final VolunteerNoteDAO volunteerNoteDAO;

  public List<VolunteerNote> findAllByVolunteerId(int volunteerId, String sortField,
      boolean descending, String searchQuery) {

    Sort sort = Sort.by(descending ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);

    List<VolunteerNote> notes = volunteerNoteDAO.findByVolunteerIdAndNoteContentContaining(volunteerId, searchQuery,
        sort);

    if (notes == null)
      throw new NoteNotFoundException("There are no Notes for Volunteer-Id: " + volunteerId);

    return notes;
  }

  @Transactional
  public VolunteerNote save(VolunteerNote note, String username) {
    if (note.getUsername() != username)
      throw new VolunteerNoteInvalidFormatException("Not allowed to overwrite notes from other users.");

    return volunteerNoteDAO.save(note);
  }

  @Transactional
  public void deleteById(int noteId, String username) {
    VolunteerNote dbNote = volunteerNoteDAO.findById(noteId);

    if (dbNote == null)
      throw new NoteNotFoundException("VolunteerNote with Id '" + noteId + "' not found.");

    if (!dbNote.getUsername().equals(username))
      throw new VolunteerNoteInvalidFormatException("Not allowed to delete notes from other users.");

    volunteerNoteDAO.deleteById(noteId);
  }

  @Transactional
  public VolunteerNote update(int noteId, Map<String, Object> fields, String username) {

    VolunteerNote dbNote = volunteerNoteDAO.findById(noteId);

    if (dbNote == null)
      throw new NoteNotFoundException("VolunteerNote with Id '" + noteId + "' not found.");

    if (!dbNote.getUsername().equals(username))
      throw new VolunteerNoteInvalidFormatException("Not allowed to edit notes from other users.");

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
