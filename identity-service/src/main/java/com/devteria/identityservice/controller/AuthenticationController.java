package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.dto.request.IntrospectRequest;
import com.devteria.identityservice.dto.request.LogoutRequest;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.dto.response.IntrospectResponse;
import com.devteria.identityservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("token")
  ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
    var result= authenticationService.authenticate(request);
     return ApiResponse.<AuthenticationResponse>builder()
       .result(result)
         .build();
  }

  @PostMapping("/introspect")
  ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
    var result= authenticationService.introspect(request);
    return ApiResponse.<IntrospectResponse>builder()
      .result(result)
      .build();
  }

  @PostMapping("/logout")
  ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
     authenticationService.logout(request);
     return ApiResponse.<Void>builder().build();
  }

}
