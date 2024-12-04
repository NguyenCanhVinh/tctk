package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.RoleRequest;
import com.devteria.identityservice.entity.Role;
import com.devteria.identityservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;

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

    return roleRepository.save(role);
  }
}
