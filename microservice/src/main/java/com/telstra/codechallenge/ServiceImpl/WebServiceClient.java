package com.telstra.codechallenge.ServiceImpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.Constants.Constants;
import com.telstra.codechallenge.DTO.Items;
import com.telstra.codechallenge.DTO.UserInformation;
import com.telstra.codechallenge.ExceptionHandling.ExceptionClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebServiceClient {
    @Value("${zero.followers.url}")
    private String url;
    @Autowired
    RestTemplate restTemplate;

    /**
     * This method is used to call GitHub external api to find the
     * oldest accounts in GitHubRepository with Zero followers
     *
     * @return List<Items>
     */
    public List<Items> getUsersData() {
        log.info("Inside WebServiceClient.getUsersInfo");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Items> items = new ArrayList<>();
        try {
            log.info("Inside try block to call external api");
            Object[] userData = new Object[]{restTemplate.getForObject(url
                    , Object.class)};
            items = Arrays.asList(userData).stream().map(o -> mapper.convertValue(o, UserInformation.class)).map(
                    UserInformation::getItems).collect(Collectors.toList()).get(0);
            log.info("outside of try block");
        } catch (RuntimeException ex) {
            log.info("Inside catch block to call external api");
            if (ex instanceof HttpClientErrorException.UnprocessableEntity) {
                log.info("HttpClientErrorException.UnprocessableEntity exception occurred");
                throw new ExceptionClass(Constants.ERROR_CODE3, Constants.Error_RESPONSE422, HttpStatus.UNPROCESSABLE_ENTITY);
            }
            if (ex instanceof HttpClientErrorException) {
                log.info("HttpClientErrorException occurred");
                throw new ExceptionClass(Constants.ERROR_CODE1, Constants.Error_RESPONSE404, HttpStatus.NOT_FOUND);
            }
        }
        log.info("Outside WebServiceClient.getUsersInfo");
        return items;
    }
}

