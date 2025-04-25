package com.devteria.identityservice.controller;

import com.devteria.identityservice.constant.ApiResponseCode;
import com.devteria.identityservice.dto.request.*;
import com.devteria.identityservice.dto.response.AuthenticationResponse;
import com.devteria.identityservice.dto.response.IntrospectResponse;
import com.devteria.identityservice.service.AuthenticationService;
import com.devteria.identityservice.utils.ApiResponseUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  private final DefaultKaptcha defaultKaptcha;

  @Autowired
    public AuthenticationController(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    @PostMapping("token")
  public ResponseEntity<Object> authentication(@RequestBody AuthenticationRequest request, HttpServletRequest httpRequest) throws ParseException, JOSEException {
    var result= authenticationService.authenticate(request, httpRequest);
    return ApiResponseUtils.create(ApiResponseCode.SUCCESS, result);
//     return ApiResponse.<AuthenticationResponse>builder()
//       .result(result)
//         .build();
  }

  @GetMapping("/captcha")
  public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {

    String text = defaultKaptcha.createText();
    BufferedImage image = defaultKaptcha.createImage(text);

    request.getSession().setAttribute("captcha", text);

    response.setContentType("image/jpeg");
    OutputStream outputStream = response.getOutputStream();
    ImageIO.write(image, "jpg", outputStream);
    outputStream.close();
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
