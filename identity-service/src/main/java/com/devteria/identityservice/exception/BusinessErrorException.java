package com.devteria.identityservice.exception;


public class BusinessErrorException extends RuntimeException implements ICommonException {

  Object[] params;

  String message;

  public BusinessErrorException params(Object... params) {
    this.params = params;
    return this;
  }

  public BusinessErrorException message(String message) {
    this.message = message;
    return this;
  }

  @Override
  public Object[] getParams() {
    return params;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
