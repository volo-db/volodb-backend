package dev.urner.volodb.entity;

public class ContactTypeNotFoundException extends RuntimeException {
  public ContactTypeNotFoundException(String message) {
    super(message);
  }
}
