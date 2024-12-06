package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.PermissionRequest;
import com.devteria.identityservice.entity.Permission;
import com.devteria.identityservice.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

  private  final PermissionService permissionService;

  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PostMapping
  public ApiResponse<Permission> createPermission(@RequestBody @Valid PermissionRequest permissionRequest){
    ApiResponse<Permission> apiResponse = new ApiResponse<>();
    apiResponse.setCode(1000);
    apiResponse.setResult(permissionService.createPermission(permissionRequest));
    return apiResponse;
  }

  @GetMapping
 public List<Permission> getAll() {
    return  permissionService.getPermission();
  }

  @DeleteMapping("/{permission}")
  public ApiResponse<Void> delete(@PathVariable String permission) {
    permissionService.deletePermission(permission);
    return ApiResponse.<Void>builder().build();
  }



}
