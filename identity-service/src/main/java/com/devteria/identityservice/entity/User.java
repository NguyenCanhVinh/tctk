package com.devteria.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private String id;
     private String username;
     private String password;
     private String firstName;
     private String lastName;
     private LocalDate dob;
     private Set<String> roles;
}
