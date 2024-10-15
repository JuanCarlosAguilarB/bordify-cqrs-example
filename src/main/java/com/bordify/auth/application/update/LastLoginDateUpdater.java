package com.bordify.auth.application.update;

import com.bordify.auth.application.find.UserFinder;
import com.bordify.auth.domain.UserDateLastLogin;
import com.bordify.auth.domain.UserWriteModel;
import com.bordify.auth.domain.UserWriteModelRepository;
import com.bordify.shared.domain.UserUserName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LastLoginDateUpdater {

    private final UserWriteModelRepository repository;
    private final UserFinder userFinder;

    public void update(UserUserName userName, UserDateLastLogin lastLoginDate) {

        // find and verify user exists
        UserWriteModel user = userFinder.findUserByUsername(userName);

        UserWriteModel userToUpdate = new UserWriteModel(
                user.id(), user.email(), user.userName(), user.password(), user.isVerified(), user.created(), new UserDateLastLogin(lastLoginDate.value()));

        repository.save(userToUpdate);

    }
}
