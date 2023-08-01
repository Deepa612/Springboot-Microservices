package com.telstra.codechallenge.ServiceImpl;

import com.telstra.codechallenge.Constants.Constants;
import com.telstra.codechallenge.DTO.UserInformation;
import com.telstra.codechallenge.ExceptionHandling.ExceptionClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WebServiceClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${zero.followers.url}")
    private String url;

    /**
     * This method is used to call GitHub external api to find the
     * oldest accounts in GitHubRepository with Zero followers
     * @return List<Items>
     */
    public UserInformation getUsersData() {
        log.info("Inside WebServiceClient.getUsersInfo");
        UserInformation userInformation = new UserInformation();
        try {
            log.info("Inside try block to call external api");
            ResponseEntity<UserInformation> response = restTemplate.getForEntity(url
                    , UserInformation.class);
            userInformation = response.getBody();
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
        return userInformation;
    }
}

