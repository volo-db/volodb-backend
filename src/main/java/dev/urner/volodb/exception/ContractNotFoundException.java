package dev.urner.volodb.exception;

public class ContractNotFoundException extends RuntimeException {
  public ContractNotFoundException(String message) {
    super(message);
  }
}
