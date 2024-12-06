package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.PermissionRequest;
import com.devteria.identityservice.entity.Permission;
import com.devteria.identityservice.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PermissionService {

  private  final PermissionRepository permissionRepository;

  public PermissionService(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  public void deletePermission(String name){
    permissionRepository.deleteById(name);
  }

  public List<Permission> getPermission(){
    return permissionRepository.findAll();
  }

  public Permission createPermission(PermissionRequest permissionRequest){

    Permission permission= new Permission();
    permission.setName(permissionRequest.getName());
    permission.setDescription(permissionRequest.getDescription());


    return permissionRepository.save(permission);


  }





}
