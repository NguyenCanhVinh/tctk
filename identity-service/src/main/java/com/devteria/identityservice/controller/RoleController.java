package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.RoleRequest;
import com.devteria.identityservice.dto.response.RoleResponse;
import com.devteria.identityservice.entity.Role;
import com.devteria.identityservice.service.RoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping
  public ApiResponse<Role> createRole(@RequestBody @Valid RoleRequest roleRequest){
    ApiResponse apiResponse= new ApiResponse();
    apiResponse.setCode(1000);
    apiResponse.setResult(roleService.createRoler(roleRequest));
    return apiResponse;

  }

  @GetMapping
  public List<Role> getAll(){
    return roleService.getRole();
  }

  @DeleteMapping("/{role}")
  public ApiResponse<Void> DeleteRole(@PathVariable String role){
    roleService.deleteRole(role);
    return ApiResponse.<Void>builder().build();

  }
}
