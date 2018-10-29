package com.blueharvest.banksystem.controller;

import com.blueharvest.banksystem.exceptions.UserNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void Giver_CustomerID_When_CallForUserInfosByCustomerIDAndUserExists_Then_GetUserInfos() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        /*request.param("nameClasses", "test1");*/

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.firstName", Matchers.is("Konstantinos")))
                .andDo(print());
    }

    @Test
    public void Giver_CustomerID_When_GetUserInfosByCustomerIDAndUserNotExists_Then_ThrowException()  {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        /*request.param("nameClasses", "test1");*/
        try {
            mockMvc.perform(request)
                    .andExpect(status().is4xxClientError())
                    .andDo(print());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("User with id 4 not found.") );

        }
    }

}
