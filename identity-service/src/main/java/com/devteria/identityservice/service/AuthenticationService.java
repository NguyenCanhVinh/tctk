package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  UserRepository userRepository;

  boolean authenticate(AuthenticationRequest request){
     var user = userRepository.findByUsername(request.getUsername())
       .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

  }


}
