package com.devteria.identityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@Getter
@Setter
@Data
public class ApiResponse<T> {
  private int code;
  private String message;
  private T data;
  private T result;
  private long timestamp;

  public ApiResponse(int code, String message, T data, T result, long timestamp) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.result = result;
    this.timestamp = new Date().toInstant().toEpochMilli();
  }

  public ApiResponse(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.timestamp = new Date().toInstant().toEpochMilli();
  }

  public ApiResponse(int code, String message) {
    this.code = code;
    this.message = message;
    this.data = null;
    this.timestamp = new Date().toInstant().toEpochMilli();
  }

}
