package com.devteria.identityservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class PermissionRequest {

  private  String name;
  private  String description;
}
