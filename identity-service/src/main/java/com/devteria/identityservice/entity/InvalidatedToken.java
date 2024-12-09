package com.devteria.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InvalidatedToken {
    @Id
    private String id;

    private Date expiryTime;

    private String status;

    private String username;

}
