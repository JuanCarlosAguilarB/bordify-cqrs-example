package com.bordify.auth.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "\"user_auth_information\"")
public class UserAuthInformationEntity {

    @Id
    private UUID userId;
    private String email;
    private String username;
    private String password;
    private Boolean isVerified;
    private LocalTime created;
    private LocalTime lastLogin;
}
