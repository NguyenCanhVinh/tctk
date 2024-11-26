package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.UserRepository;
import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  public AuthenticationResponse authenticate(AuthenticationRequest request){
    PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
     var user = userRepository.findByUsername(request.getUsername())
       .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

     boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

     if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

  }

  private  String generatePassword(String username){
      JWSObject jwsObject = new JWSObject();

  }


}
