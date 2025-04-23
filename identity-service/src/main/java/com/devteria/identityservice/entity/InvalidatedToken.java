package com.devteria.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Setter
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity
public class InvalidatedToken {
    @Id
    private String id;

    private Date expiryTime;

    private String status;

    private String username;

}
