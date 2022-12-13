package com.jmalik.kyte.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response<T> {

  private T data;
  private HttpStatus httpStatus;
  private String errorMessage;

  public static <T> Response<T> success(T data) {
    Response<T> response = new Response<>();
    response.setData(data);
    response.setHttpStatus(HttpStatus.OK);
    return response;
  }

  public static <T> Response<T> badRequest(String errorMessage) {
    Response<T> response = new Response<>();
    response.setHttpStatus(HttpStatus.BAD_REQUEST);
    response.setErrorMessage(errorMessage);
    return response;
  }

  public static <T> Response<T> internalError(String errorMessage) {
    Response<T> response = new Response<>();
    response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    response.setErrorMessage(errorMessage);
    return response;
  }
}
