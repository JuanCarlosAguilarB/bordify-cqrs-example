package com.bordify.user.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static com.bordify.user.domain.UserFactory.createRandomUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserGetControllerShould extends TestCaseController {

    @Autowired
    private UserRepository repository;

    @Test
    public void shouldReturnMeInformation() throws Exception{

        String url = "/v1/users/me/";
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        result.andDo(print());
//        result.andExpect(content().json(Matchers.notNullValue().toString()));
//        result.andExpect(jsonPath("$.username").value(CoreMatchers.containsString(this.user().getUsername())));
//        result.andExpect(jsonPath("$.email", CoreMatchers.containsString(this.user().getEmail())));
//        result.andExpect(jsonPath("$.firstName", CoreMatchers.containsString(this.user().getFirstName())));

        result
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.id").value(this.user().getId().toString()))
            .andExpect(jsonPath("$.username").value(this.user().getUsername()))
            .andExpect(jsonPath("$.email").value(this.user().getEmail()))
            .andExpect(jsonPath("$.firstName").value(this.user().getFirstName()))
            .andExpect(jsonPath("$.lastName").value(this.user().getLastName()))
//            .andExpect(jsonPath("$.isVerified").value(this.user().getIsVerified()))
            .andExpect(jsonPath("$.phoneNumber").value(this.user().getPhoneNumber()))
            .andExpect(jsonPath("$.created").value(this.user().getCreated()))
            .andExpect(jsonPath("$.lastLogin").value(this.user().getLastLogin()));

    }



    @Test
    public void shouldNotReturnMeSensibleInformation() throws Exception{

        String url = "/v1/users/me/";
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        result
            .andDo(print())
            .andExpect(jsonPath("$.userName").exists())
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.password").doesNotExist())
            .andExpect(jsonPath("$.isVerified").doesNotExist());

    }

    @Test
    public void shouldReturnOnlyMeInformation() throws Exception{


        User anotherUser = createRandomUser();
        repository.save(anotherUser);

        String url = "/v1/users/me/";
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        result.andDo(print());

        result
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(this.user().getId().toString()))
                .andExpect(jsonPath("$.username").value(this.user().getUsername()))
                .andExpect(jsonPath("$.email").value(this.user().getEmail()))
                .andExpect(jsonPath("$.firstName").value(this.user().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(this.user().getLastName()))
//            .andExpect(jsonPath("$.isVerified").value(this.user().getIsVerified()))
                .andExpect(jsonPath("$.phoneNumber").value(this.user().getPhoneNumber()))
                .andExpect(jsonPath("$.created").value(this.user().getCreated()))
                .andExpect(jsonPath("$.lastLogin").value(this.user().getLastLogin()));

    }


}
