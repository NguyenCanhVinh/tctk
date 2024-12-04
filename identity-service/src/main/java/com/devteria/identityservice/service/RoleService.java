package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.RoleRequest;
import com.devteria.identityservice.entity.Role;
import com.devteria.identityservice.repository.PermissionRepository;
import com.devteria.identityservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;

  public List<Role> getRole(){
    return  roleRepository.findAll();
  }


  public void  deleteRole(String role){
    roleRepository.deleteById(role);
  }


  public Role createRoler(RoleRequest request){
    Role role = new Role();
    role.setName(request.getName());
    role.setDescription(request.getDescription());

    var permissions=permissionRepository.findAllById(request.getPermissions());

    role.setPermissions(new HashSet<>(permissions));

    return roleRepository.save(role);
  }
}
