package com.blueharvest.banksystem.controller;

import com.blueharvest.banksystem.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void When_PostAccountAndUserExists_Then_SaveAccount() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setCustomerID(1L);
        account.setInitialCredit(100F);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account));

        mockMvc.perform(request)
                .andExpect(jsonPath("$.id", is(1)));



    }
    @Test
    public void When_PostAccountAndUserNotExists_Then_ThrowException() throws JsonProcessingException {
        Account account = new Account();
        account.setId(1L);
        account.setCustomerID(4L);
        account.setInitialCredit(100F);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account));

        try {
            mockMvc.perform(request)
                    .andExpect(status().is4xxClientError())
                    .andDo(print());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("User with id 4 not found.") );

        }
    }
}
