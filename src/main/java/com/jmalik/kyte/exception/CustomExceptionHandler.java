package com.jmalik.kyte.exception;

import com.jmalik.kyte.controller.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Response> handleBadRequestException(BadRequestException exception) {
    log.error("Bad Request Exception - ", exception);
    return ResponseEntity.ok(Response.badRequest(exception.getErrorMessage()));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException exception) {
    log.error("Resource Not Found Exception - ", exception);
    return ResponseEntity.ok(Response.badRequest(exception.getErrorMessage()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Response> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
    log.error("Data Integrity Violation Exception - ", exception);
    return ResponseEntity.ok(Response.badRequest(exception.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Response> handleRuntimeException(RuntimeException exception) {
    log.error("Unknown Runtime Exception - ", exception);
    return ResponseEntity.ok(Response.internalError(exception.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response> handleException(Exception exception) {
    log.error("Unknown Exception - ", exception);
    return ResponseEntity.ok(Response.internalError(exception.getMessage()));
  }
}
