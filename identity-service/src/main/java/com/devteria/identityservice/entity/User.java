package com.devteria.identityservice.entity;

import com.devteria.identityservice.export.ExtraFieldCriteria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User extends ExtraFieldCriteria implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private String id;
     private String username;
     private String password;
     private String firstName;
     private String lastName;
     private LocalDate dob;

     @ManyToMany
     private Set<Role> roles;
}
