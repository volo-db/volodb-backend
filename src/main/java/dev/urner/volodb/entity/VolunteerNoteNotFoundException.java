package dev.urner.volodb.entity;

public class VolunteerNoteNotFoundException extends RuntimeException {
  public VolunteerNoteNotFoundException(String message) {
    super(message);
  }
}
