package com.telstra.codechallenge;

import com.telstra.codechallenge.responsedto.Items;
import com.telstra.codechallenge.responsedto.UserInformation;
import com.telstra.codechallenge.serviceimpl.GitHubUsersServiceImpl;
import com.telstra.codechallenge.serviceimpl.WebServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceTests {


    @InjectMocks
    GitHubUsersServiceImpl gitUsersServiceImpl;
    @Mock
    WebServiceClient webServiceClient;


    @Test
    void getUsersInfo() {
        Integer number = 1;
        UserInformation userInformation = new UserInformation();
        Items items = new Items();
        List<Items> itemsList = new ArrayList<>();
        items.setId(3);
        items.setLogin("mattetti");
        items.setHtml_url("https://github.com/mattetti");
        itemsList.add(items);
        userInformation.setItems(itemsList);
        Mockito.when(webServiceClient.getUsersData()).thenReturn(userInformation);
        List<Items> itemsList1 = gitUsersServiceImpl.getUsersInfo(number);
        assertNotNull(itemsList1);

    }

    @Test
    void getUsersInfoEmptyItems() {
        Integer number = 2;
        List<Items> itemsList = new ArrayList<>();
        UserInformation userInformation = new UserInformation();
        userInformation.setItems(itemsList);
        Mockito.when(webServiceClient.getUsersData()).thenReturn(userInformation);
        assertThrows(Exception.class, () -> gitUsersServiceImpl.getUsersInfo(number));

    }


}
