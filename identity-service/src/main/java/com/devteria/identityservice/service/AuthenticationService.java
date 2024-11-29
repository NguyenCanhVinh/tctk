package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.AuthenticationRequest;
import com.devteria.identityservice.dto.request.IntrospectRequest;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.dto.response.IntrospectResponse;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Data;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  @NonFinal
  @Value("${jwt.signerKey}")
  protected String SIGNER_KEY;


  public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {

    var token= request.getToken();

    JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

    SignedJWT signedJWT = SignedJWT.parse(token);

    Date exprityTime=  signedJWT.getJWTClaimsSet().getExpirationTime();

    var verified= signedJWT.verify(verifier);

    return  IntrospectResponse.builder()
      .valid(verified && exprityTime.after(new Date()))
      .build();


  }
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
