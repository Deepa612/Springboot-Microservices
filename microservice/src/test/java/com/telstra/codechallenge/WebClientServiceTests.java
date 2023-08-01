package com.telstra.codechallenge;


import com.telstra.codechallenge.constants.ErrorConstants;
import com.telstra.codechallenge.exceptionhandling.ExceptionClass;
import com.telstra.codechallenge.responsedto.Items;
import com.telstra.codechallenge.responsedto.UserInformation;
import com.telstra.codechallenge.serviceimpl.WebServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WebClientServiceTests {

    @InjectMocks
    WebServiceClient webServiceClient;

    @Mock
    RestTemplate restTemplate;
    @Value("${zero.followers.url}")
    private String url;

    @Test
    void getUsersInfo() {
        UserInformation userInformation = new UserInformation();
        List<Items> itemsList = new ArrayList<>();
        Items items = new Items();
        items.setId(3);
        items.setLogin("mattetti");
        items.setHtml_url("https://github.com/mattetti");
        itemsList.add(items);
        userInformation.setItems(itemsList);
        Mockito.when(restTemplate.getForEntity(url, UserInformation.class)).thenReturn(ResponseEntity.of(Optional.of(userInformation)));
        UserInformation userInformation1 = webServiceClient.getUsersData();
        assertNotNull(userInformation1);
    }

    @Test()
    void getUsersInfoInvalidParameterValueException() {
        Mockito.when(webServiceClient.getUsersData()).thenThrow(new ExceptionClass(ErrorConstants.ERROR_CODE_3, ErrorConstants.ERROR_RESPONSE_422, HttpStatus.UNPROCESSABLE_ENTITY));

    }

    @Test()
    void getUsersInfoInvalidURLException() {
        Mockito.when(webServiceClient.getUsersData()).thenThrow(new ExceptionClass(ErrorConstants.ERROR_CODE_1, ErrorConstants.ERROR_RESPONSE_404, HttpStatus.NOT_FOUND));

    }



}
