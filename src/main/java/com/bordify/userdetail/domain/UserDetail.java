package com.bordify.userdetail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;


@Data
@Builder
@ToString
@AllArgsConstructor
public class UserDetail {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
}
