package com.airway.demo.tools;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to be the result of all the APIs in this application
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class CustomResponseBody<T> {

  private String status;
  private String message;
  @JsonInclude
  private T      data;

  public CustomResponseBody(final T value, final String status, String message) {
    this.data = value;
    this.status = status;
    this.message = message;
  }

  public CustomResponseBody(final T value, final String status) {
    this.setData(value);
    this.status = status;
  }

}