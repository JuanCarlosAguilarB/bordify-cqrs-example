package com.bordify.auth.domain;

import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.UserUserName;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class UserReadModel {

    private final UserUserId userId;
    private final UserEmail email;
    private final UserUserName userName;
    private final UserIsVerified isVerified;
    private final UserDateCreated created;
    private final UserDateLastLogin lastLogin;

    public UserReadModel(UserUserId userId, UserEmail email, UserUserName userName, UserIsVerified isVerified, UserDateCreated created, UserDateLastLogin lastLogin) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.isVerified = isVerified;
        this.created = created;
        this.lastLogin = lastLogin;
    }

    public UserUserId id() {
        return userId;
    }

    public UserEmail email() {
        return email;
    }

    public UserUserName userName() {
        return userName;
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
