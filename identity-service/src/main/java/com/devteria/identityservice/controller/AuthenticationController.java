package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("log-in")
  ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
    boolean result= authenticationService.authenticate(request);
     return ApiResponse.<AuthenticationResponse>builder()
       .result(AuthenticationResponse.builder().authenticated(result)
         .build()).build();
  }
}
