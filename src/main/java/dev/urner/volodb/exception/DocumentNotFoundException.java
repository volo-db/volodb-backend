package dev.urner.volodb.exception;

public class DocumentNotFoundException extends RuntimeException {
  public DocumentNotFoundException(String message) {
    super(message);
  }
}
