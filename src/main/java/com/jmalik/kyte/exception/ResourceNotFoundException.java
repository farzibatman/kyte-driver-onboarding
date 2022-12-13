package com.jmalik.kyte.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

  private static final String RESOURCE_NOT_FOUND_MESSAGE_FORMAT = "Object %s Id %s not found";

  private String errorMessage;

  public ResourceNotFoundException(String objectName, String id) {
    super(String.format(RESOURCE_NOT_FOUND_MESSAGE_FORMAT, objectName, id));
    this.errorMessage = String.format(RESOURCE_NOT_FOUND_MESSAGE_FORMAT, objectName, id);
  }
}
