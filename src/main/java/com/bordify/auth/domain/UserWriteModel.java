package com.bordify.auth.domain;

import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.UserUserName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserWriteModel {

    private final UserUserId userId;
    private final UserEmail email;
    private final UserUserName userName;
    private final UserPassword password;
    private final UserIsVerified isVerified;
    private final UserDateCreated created;
    private final UserDateLastLogin lastLogin;

    public UserUserId id() {
        return userId;
    }

    public UserEmail email() {
        return email;
    }

    public UserUserName userName() {
        return userName;
    }

    public UserPassword password() {
        return password;
    }

    public UserIsVerified isVerified() {
        return isVerified;
    }

    public UserDateCreated created() {
        return created;
    }

    public UserDateLastLogin lastLogin() {
        return lastLogin;
    }

}
