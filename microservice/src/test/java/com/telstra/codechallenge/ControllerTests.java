package com.telstra.codechallenge;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.controllers.GitHubUserController;
import com.telstra.codechallenge.responsedto.Items;
import com.telstra.codechallenge.service.GitHubUsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = GitHubUserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ControllerTests {
    @MockBean
    GitHubUsersService gitUsersService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUsersInfoTest() throws Exception {
        Integer number = 1;
        String URI = "/v1/zero-followers/userAccounts/1";
        Items items = new Items();
        List<Items> itemsList = new ArrayList<>();
        items.setId(3);
        items.setLogin("mattetti");
        items.setHtml_url("https://github.com/mattetti");
        itemsList.add(items);
        Mockito.when(gitUsersService.getUsersInfo(number)).thenReturn(itemsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).param("limit", String.valueOf(1)).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedValue = this.mapToJson(itemsList);
        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(expectedValue, response);


    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
