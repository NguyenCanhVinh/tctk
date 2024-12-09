package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.*;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.dto.response.IntrospectResponse;
import com.devteria.identityservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
    var result= authenticationService.authenticate(request);
     return ApiResponse.<AuthenticationResponse>builder()
       .result(result)
         .build();
  }

  @PostMapping("/introspect")
  public ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
    var result= authenticationService.introspect(request);
    return ApiResponse.<IntrospectResponse>builder()
      .result(result)
      .build();
  }

  @PostMapping("/logout")
  public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
     authenticationService.logout(request);
     return ApiResponse.<Void>builder().build();
  }

  @PostMapping("/refresh")
  public ApiResponse<AuthenticationResponse> authentication(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
    var result= authenticationService.refreshToken(request);
    return ApiResponse.<AuthenticationResponse>builder()
            .result(result)
            .build();
  }

}
