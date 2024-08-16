package com.bordify.auth.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "\"user_auth_information\"")
public class UserWriteModelEntity {

    @Id
    private UUID userId;
    private String email;
    private String userName;
    private String password;
    private Boolean isVerified;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
}
