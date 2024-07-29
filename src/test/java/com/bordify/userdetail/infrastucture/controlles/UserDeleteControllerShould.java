package com.bordify.userdetail.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.userdetail.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

public class UserDeleteControllerShould extends TestCaseController {

    @Test
    public void shouldDeleteAnUser() throws Exception {

        User user = createRandomPersistentUser();
        String uri = buildUrl("/v1/users/{id}/", user.getId().toString());

        assertRequest(HttpMethod.DELETE, uri, 200, true);

    }


}
