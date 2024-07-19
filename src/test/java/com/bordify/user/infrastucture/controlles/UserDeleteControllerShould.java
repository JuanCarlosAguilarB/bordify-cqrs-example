package com.bordify.user.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

public class UserDeleteControllerShould extends TestCaseController {

    @Test
    public void shouldDeleteAnUser() throws Exception {

        User user = createRandomPersistentUser();
        String uri = buildUrl("/v1/users/{id}/", user.getId().toString());

        assertRequest( HttpMethod.DELETE, uri, 200, true);

    }


}
