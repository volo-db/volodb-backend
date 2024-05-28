package dev.urner.volodb.exception;

public class ContactTypeNotFoundException extends RuntimeException {
  public ContactTypeNotFoundException(String message) {
    super(message);
  }
}
