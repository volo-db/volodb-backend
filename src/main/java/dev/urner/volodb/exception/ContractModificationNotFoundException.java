package dev.urner.volodb.exception;

public class ContractModificationNotFoundException extends RuntimeException {
  public ContractModificationNotFoundException(String message) {
    super(message);
  }
}
