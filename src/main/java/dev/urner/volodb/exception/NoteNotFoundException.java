package dev.urner.volodb.exception;

public class NoteNotFoundException extends RuntimeException {
  public NoteNotFoundException(String message) {
    super(message);
  }
}
