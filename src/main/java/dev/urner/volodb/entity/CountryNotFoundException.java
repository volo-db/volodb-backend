package dev.urner.volodb.entity;

public class CountryNotFoundException extends RuntimeException {
  public CountryNotFoundException(String message) {
    super(message);
  }
}
