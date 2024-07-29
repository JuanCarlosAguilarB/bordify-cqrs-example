package com.bordify.userdetail.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

public class UserDetailDeleteControllerShould extends TestCaseController {

    @Test
    public void shouldDeleteAnUser() throws Exception {

        User user = createRandomPersistentUser();
        String uri = buildUrl("/v1/users/{id}/", user.getId().toString());

        assertRequest(HttpMethod.DELETE, uri, 200, true);

    }


}
