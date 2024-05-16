package dev.urner.volodb.entity;

public class AddressStatusNotFoundException extends RuntimeException {
  public AddressStatusNotFoundException(String message) {
    super(message);
  }
}
