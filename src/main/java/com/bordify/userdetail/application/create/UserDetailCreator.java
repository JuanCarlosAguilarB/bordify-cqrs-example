package com.bordify.userdetail.application.create;

import com.bordify.auth.domain.DuplicateEmailException;
import com.bordify.shared.domain.UserUserName;
import com.bordify.userdetail.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Service class for user-related operations.
 */
@Service
@AllArgsConstructor
public class UserDetailCreator {

    private final UserDetailRepository userDetailRepository;

    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @param user The user object containing the user details to be created.
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(
            UserDetailId id,
            UserDetailFirstName firstName,
            UserDetailLastName lastName,
            UserDetailPhoneNumber phoneNumber,
            UserUserName username
    ) {
        UserDetail user = new UserDetail(id, firstName, lastName, phoneNumber, username);
        userDetailRepository.save(user);

    }

}
