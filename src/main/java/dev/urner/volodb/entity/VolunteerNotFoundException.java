package dev.urner.volodb.entity;

public class VolunteerNotFoundException extends RuntimeException {
  public VolunteerNotFoundException(String message) {
    super(message);
  }
}
