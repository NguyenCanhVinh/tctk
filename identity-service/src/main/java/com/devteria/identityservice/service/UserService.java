package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.enums.Role;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.repository.RoleRepository;
import com.devteria.identityservice.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

       // user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        var roles= roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @Secured({"delete"})
    public List<User> getUsers(){
        log.info("in method get users");
        return userRepository.findAll();
    }
    @PostAuthorize("returnObject.username == authentication.name")
    public User getUser(String id){
        log.info("in method get users by id");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user= userRepository.findByUsername(name).orElseThrow(
          ()-> new AppException(ErrorCode.USER_EXISTED)
        );

        UserResponse userResponse= new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDob(user.getDob());
//        userResponse.setRoles(user.getRoles());

        return userResponse;
    }

    public void saveCustomersToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<User> users = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
                this.userRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    public List<User> exportCustomerToExcel(HttpServletResponse response) throws IOException {
        List<User> users = userRepository.findAll();
        ExcelExportUtils exportUtils = new ExcelExportUtils(users);
        exportUtils.exportDataToExcel(response);
        return users;
    }

}
