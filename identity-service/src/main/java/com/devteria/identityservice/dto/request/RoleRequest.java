package com.devteria.identityservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class RoleRequest {

  private String name;
  private String description;
  private Set<String> permissions;
}
