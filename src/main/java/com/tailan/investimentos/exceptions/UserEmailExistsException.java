package com.tailan.investimentos.exceptions;

public class UserEmailExistsException extends RuntimeException {
  public UserEmailExistsException(String message) {
    super(message);
  }
}
