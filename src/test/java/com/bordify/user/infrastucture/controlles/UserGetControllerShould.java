package com.bordify.user.infrastucture.controlles;

import com.bordify.shared.infrastucture.controlles.TestCaseController;
import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import static com.bordify.user.domain.UserFactory.createRandomUser;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
//            .andExpect(jsonPath("$.created").value(this.user().getCreated().toString()))
//            .andExpect(jsonPath("$.lastLogin").value(this.user().getLastLogin()))
        ;

    }


    @Test
    public void shouldNotReturnMeSensibleInformation() throws Exception{

        String url = "/v1/users/me/";
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        result
            .andDo(print())
            .andExpect(jsonPath("$.username").exists())
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.password").doesNotExist())
            .andExpect(jsonPath("$.isVerified").doesNotExist())
            .andExpect(jsonPath("$.created").doesNotExist())
            .andExpect(jsonPath("$.lastLogin").doesNotExist());
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
//                .andExpect(jsonPath("$.created", Matchers.equalTo(this.user().getCreated().toString())))
//                .andExpect(jsonPath("$.lastLogin").value(this.user().getLastLogin()))
                ;

    }

    @Test
    public void shouldReturnListOfUsers() throws Exception {

        int numberMaxUsersCreated = (int) ((Math.random() * (10 - 1)) + 1);

        for (int i = 0; i<numberMaxUsersCreated; i++){
            repository.save(createRandomUser());
        }
        System.out.println("Users created: " + numberMaxUsersCreated);

        User userRegistered = createRandomPersistentUser();

        String url = "/v1/users/";
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        result
            // we have numberMaxUsersCreated + 1 users, bewcause we createToken an user
            .andExpect(jsonPath("$.content", hasSize(numberMaxUsersCreated+1))) // Validate content size
            .andExpect(jsonPath("$.totalElements").value(numberMaxUsersCreated+1)) // Validate total elements
            // Ensure userRegistered is not in the list --> this maybe will be able to a feature
//            .andExpect(jsonPath("$.content[*].username", not(hasItem(userRegistered.getUsername()))))
        ;
    }

    @Test
    public void shouldReturnListOfUsersWithPaginationSpecified() throws Exception {

        int numberMaxUsersCreated = (int) ((Math.random() * (15 - 1)) + 1);
        int pageSize = (int) ((Math.random() * (15 - 1)) + 1);

        for (int i = 0; i<numberMaxUsersCreated; i++){
            repository.save(createRandomUser());
        }
        System.out.println("Users created: " + numberMaxUsersCreated);

        User userRegistered = createRandomPersistentUser();



        String url = "/v1/users/?pageSize="+pageSize;
        ResultActions result = assertRequest(HttpMethod.GET, url, 200, true);

        // we have numberMaxUsersCreated + 1 users, bewcause we createToken an user
        int numberUsersExpected = Math.min(numberMaxUsersCreated+1, pageSize);

        result

                .andExpect(jsonPath("$.content", hasSize(numberUsersExpected))) // Validate content size
                .andExpect(jsonPath("$.totalElements").value(numberMaxUsersCreated + 1)) // Validate total elements
                // Ensure userRegistered is not in the list
//                .andExpect(jsonPath("$.content[*].username", not(hasItem(userRegistered.getUsername()))))
        ;
    }

}







