package com.bordify.auth.application.find;

import com.bordify.shared.domain.UserUserName;
import com.bordify.auth.domain.UserWriteModel;
import com.bordify.auth.domain.UserWriteModelRepository;
import com.bordify.shared.domain.UserUserId;
import com.bordify.userdetail.domain.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFinder {

    private final UserWriteModelRepository repository;

    /**
     * Retrieves a user by the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserDetail object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public UserWriteModel findUserByUsername(UserUserName username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found with username: " + username));
    }

    public UserWriteModel findById(UserUserId userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found with id: " + userId));
    }

}
