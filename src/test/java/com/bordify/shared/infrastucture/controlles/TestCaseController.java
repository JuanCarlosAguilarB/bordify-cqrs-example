package com.bordify.shared.infrastucture.controlles;


import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.bordify.user.domain.UserFactory.createRandomUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@AutoConfigureMockMvc
@Transactional
abstract public class TestCaseController {


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private  UserRepository repository;

    private String userRegistered;
    private final UUID userId = UUID.randomUUID();

    public  User createRandomPersistentUser() {
        User user = createRandomUser();
        repository.save(user);
        return user;

    }

    public ResultActions assertRequestWithBody (
            HttpMethod method,
            String url,
            Object body,
            int expectedStatusCode,
            String expectedResponse
    ) throws Exception {

        ResultMatcher bodyEspexted = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);

        return mockMvc.perform(
                MockMvcRequestBuilders.request(method, url)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().is(expectedStatusCode))
                .andExpect(bodyEspexted);
//                .andReturn();

    }

    public ResultActions assertRequestWithBody (
            HttpMethod method,
            String url,
            Object body,
            int expectedStatusCode
    ) throws Exception {


        return mockMvc.perform(
                        MockMvcRequestBuilders.request(method, url)
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                                .accept(APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andExpect(status().is(expectedStatusCode));
//                .andReturn();

    }


    public void assertRequest (
            HttpMethod method, String url,
            int expectedStatusCode,
            String expectedResponse
    ) throws Exception {

        ResultMatcher bodyEspexted = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.request(method, url)
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andExpect(status().is(expectedStatusCode))
                .andExpect(bodyEspexted)
                .andReturn();

    }
}
