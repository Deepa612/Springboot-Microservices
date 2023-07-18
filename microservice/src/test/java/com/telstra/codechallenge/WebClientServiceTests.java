package com.telstra.codechallenge;


import com.telstra.codechallenge.Constants.Constants;
import com.telstra.codechallenge.DTO.Items;
import com.telstra.codechallenge.DTO.UserInformation;
import com.telstra.codechallenge.ExceptionHandling.ExceptionClass;
import com.telstra.codechallenge.ServiceImpl.WebServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WebClientServiceTests {

    @InjectMocks
    WebServiceClient webServiceClient;

    @Mock
    RestTemplate restTemplate;
    @Value("${zero.followers.url}")
    private String url;

    @Test
    public void getUsersInfo() {
        Items items = new Items();
        List<Items> itemsList = new ArrayList<>();
        items.setId(3);
        items.setLogin("mattetti");
        items.setHtml_url("https://github.com/mattetti");
        itemsList.add(items);
        UserInformation userInformation = new UserInformation();
        userInformation.setItems(itemsList);

        when(restTemplate.getForObject(url, Object.class)).thenReturn(userInformation);
        List<Items> itemsList1 = webServiceClient.getUsersData();
        assertNotNull(itemsList1);
    }

    @Test()
    public void getUsersInfoInvalidParameterValueException() {
        when(webServiceClient.getUsersData()).thenThrow(new ExceptionClass(Constants.ERROR_CODE3, Constants.Error_RESPONSE422, HttpStatus.UNPROCESSABLE_ENTITY));

    }

    @Test()
    public void getUsersInfoInvalidURLException() {
        when(webServiceClient.getUsersData()).thenThrow(new ExceptionClass(Constants.ERROR_CODE1, Constants.Error_RESPONSE404, HttpStatus.NOT_FOUND));

    }


}
