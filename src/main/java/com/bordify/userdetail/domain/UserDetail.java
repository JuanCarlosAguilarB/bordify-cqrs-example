package com.bordify.userdetail.domain;

import com.bordify.shared.domain.UserUserId;
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

    private UserDetailId id;
    private UserDetailFirstName firstName;
    private UserDetailLastName lastName;
    private UserDetailPhoneNumber phoneNumber;
    private UserUserId username;

}
