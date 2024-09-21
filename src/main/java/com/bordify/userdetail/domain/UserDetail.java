package com.bordify.userdetail.domain;

import com.bordify.shared.domain.UserUserName;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
public class UserDetail {

    private UserDetailId id;
    private UserDetailFirstName firstName;
    private UserDetailLastName lastName;
    private UserDetailPhoneNumber phoneNumber;
    private UserUserName userName;

    public UserDetailId id() {
        return id;
    }

    public UserDetailFirstName firstName() {
        return firstName;
    }

    public UserDetailLastName lastName() {
        return lastName;
    }

    public UserDetailPhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public UserUserName userName() {
        return userName;
    }
}
