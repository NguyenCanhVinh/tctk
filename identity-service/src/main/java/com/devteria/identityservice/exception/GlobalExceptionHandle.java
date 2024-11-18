package com.devteria.identityservice.exception;

import com.devteria.identityservice.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {

  @ExceptionHandler(value = Exception.class)
  ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e){

    ApiResponse apiResponse= new ApiResponse();
    apiResponse.setCode(9999);
    apiResponse.setMessage(e.getMessage());

    return  ResponseEntity.badRequest().body(apiResponse);
  }

  @ExceptionHandler(value = AppException.class)
  ResponseEntity<ApiResponse> handlingAppException(AppException e){

    ErrorCode errorCode= e.getErrorCode();
    ApiResponse apiResponse= new ApiResponse();
    apiResponse.setCode(errorCode.getCode());
    apiResponse.setMessage(errorCode.getMessage());

    return  ResponseEntity.badRequest().body(apiResponse);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  ResponseEntity<String> habdlingValidationException(MethodArgumentNotValidException e){
    return  ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
  }
}
