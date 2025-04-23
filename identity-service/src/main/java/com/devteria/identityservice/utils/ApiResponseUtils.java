package com.devteria.identityservice.utils;

import com.devteria.identityservice.constant.ApiResponseCode;
import com.devteria.identityservice.dto.request.ApiResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseUtils {
    public static ResponseEntity<Object> create(ApiResponseCode apiResponseCode) {
        ApiResponse<Object> response = new ApiResponse<>(apiResponseCode.getStatus().value(), apiResponseCode.getMessage());
        return new ResponseEntity<>(response, apiResponseCode.getStatus());
    }

    public static <T> ResponseEntity<Object> create(ApiResponseCode apiResponseCode, T data) {
        ApiResponse<Object> response = new ApiResponse<>(apiResponseCode.getStatus().value(), apiResponseCode.getMessage(), data);
        return new ResponseEntity<>(response, apiResponseCode.getStatus());
    }
}