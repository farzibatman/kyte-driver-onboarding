package com.jmalik.kyte.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

  private String errorMessage;

  public BadRequestException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  public BadRequestException(String errorMessageFormat, String ... params) {
    super(String.format(errorMessageFormat, params));
    this.errorMessage = String.format(errorMessageFormat, params);
  }
}
