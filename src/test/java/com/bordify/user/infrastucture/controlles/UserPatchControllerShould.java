package com.bordify.user.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

public class UserPatchControllerShould  extends TestCaseController {

//    values to modify

//    values can be modify:
    //    String      username;
    //    String      password;
    //    String      email;
    //    String      firstName;
    //    String      lastName;
    //    String      phoneNumber;

//    values can't be modify by the user:
    //     UUID id;
    //     Boolean     isVerified;
    //     LocalTime   created;
    //     LocalTime   lastLogin;

    private final String url = buildUrl("/v1/users/{id}", this.user().getId().toString());


    @Test
    public void shouldUpdateUsername() throws Exception {
        User userToUpdate = createRandomPersistentUser();
        Map<String, String> data = Map.of("username",userToUpdate.getUsername());
        assertRequestWithBody(
                HttpMethod.PATCH,
                url,
                data,
                200,
                true
        );

    }
    @Test
    public void shouldUpdatePassword() throws Exception {

    }
    @Test
    public void shouldUpdateEmail() throws Exception {

    }
    @Test
    public void shouldUpdateFirstName() throws Exception {

    }
    @Test
    public void shouldUpdateLastName() throws Exception {

    }
    @Test
    public void shouldUpdatePhoneNumber() throws Exception {

    }
    @Test
    public void shouldUpdateIsVerified() throws Exception {

    }
    @Test
    public void shouldUpdateCreated() throws Exception {

    }
    @Test
    public void shouldUpdateLastLogin()  throws Exception {
    }

}
