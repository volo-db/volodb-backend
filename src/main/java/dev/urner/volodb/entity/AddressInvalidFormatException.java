package dev.urner.volodb.entity;

public class AddressInvalidFormatException extends RuntimeException {
  public AddressInvalidFormatException(String message) {
    super(message);
  }
}
