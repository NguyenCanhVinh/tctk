package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  @NonFinal
  protected static final String SIGNER_KEY="LNoSyo0dt7Y603iqmCRPxohK23cMcAtyHNmTsviwFqRuAl1GCPKkcUOGUmZvw/cM";

  public AuthenticationResponse authenticate(AuthenticationRequest request){
    PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);

     var user = userRepository.findByUsername(request.getUsername())
       .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

     boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
     if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

     var token= generatePassword(request.getUsername());

     return  AuthenticationResponse.builder()
       .token(token)
       .authenticated(true)
       .build();

  }

  private  String generatePassword(String username){
    JWSHeader header= new JWSHeader(JWSAlgorithm.HS512);

    JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
      .subject(username)
      .issuer("devteri.com")
      .issueTime(new Date())
      .expirationTime(new Date(
        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
      ))
      .claim("customClaim", "custom")
      .build();

    Payload payload = new Payload(jwtClaimsSet.toJSONObject());

    JWSObject jwsObject = new JWSObject(header, payload);

    try {
      jwsObject.sign(new MACSigner(SIGNER_KEY));
      return jwsObject.serialize();
    } catch (JOSEException e) {
      log.error("can not create token");
      throw new RuntimeException(e);
    }

  }


}
