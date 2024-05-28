package dev.urner.volodb.exception;

public class CountryNotFoundException extends RuntimeException {
  public CountryNotFoundException(String message) {
    super(message);
  }
}
