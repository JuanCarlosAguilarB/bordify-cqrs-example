package com.bordify.userdetails.infrastucture.controlles;


import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.userdetails.domain.User;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static com.bordify.userdetails.domain.UserFactory.createRandomUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class UserPutControllerShould extends TestCaseController {


    @Test
    public void shouldCreateUser() throws Exception {

        User user = createRandomUser();

        String uri = String.format("/v1/users/%s/", user.getId());
        Map<String, String> expectedResponse = Map.of("message", "User created");

        assertRequestWithBody(
                HttpMethod.PUT,
                uri,
                user,
                201,
                objectMapper.writeValueAsString((expectedResponse)),
                false
        );

    }

    @Test
    public void shouldNotCreateUserByDuplicatedEmail() throws Exception {

        User user = createRandomPersistentUser();

        String uri = String.format("/v1/users/%s/", user.getId());
        Map<String, String> expectedResponse = Map.of("message", "User created");

        ResultActions response = assertRequestWithBody(
                HttpMethod.PUT,
                uri,
                user,
                409, // status().isConflict()
                false
        );

        response.andExpect(jsonPath(
                        "$.message",
                        CoreMatchers.containsString(user.getEmail())
                )
        );

    }
}
