package com.devteria.identityservice.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {
    SUCCESS(HttpStatus.OK, Constant.ResponseCode.SUCCESS, Constant.ResponseMessage.SUCCESS),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR, Constant.ResponseCode.ERROR, Constant.ResponseMessage.ERROR),
    CREATED(HttpStatus.CREATED, Constant.ResponseCode.CREATED, Constant.ResponseMessage.CREATED),
    NOT_EXIST(HttpStatus.NOT_FOUND, Constant.ResponseCode.NOT_FOUND, Constant.ResponseMessage.NOT_FOUND),
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST, Constant.ResponseCode.BAD_REQUEST, Constant.ResponseMessage.ALREADY_EXIST),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, Constant.ResponseCode.BAD_REQUEST, Constant.ResponseMessage.INVALID_INPUT),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, Constant.ResponseCode.UNAUTHORIZED, Constant.ResponseMessage.UNAUTHORIZED),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, Constant.ResponseCode.BAD_REQUEST, Constant.ResponseMessage.BAD_REQUEST),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    public String formatErrorMessage(String fieldName) {
        return message.formatted(fieldName);
    }
}

