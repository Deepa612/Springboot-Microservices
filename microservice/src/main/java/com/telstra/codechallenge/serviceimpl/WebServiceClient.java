package com.telstra.codechallenge.serviceimpl;

import com.telstra.codechallenge.constants.ErrorConstants;
import com.telstra.codechallenge.exceptionhandling.ExceptionClass;
import com.telstra.codechallenge.responsedto.UserInformation;
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
    @Value("${gitHub.base.url}")
    private String baseurl;
    @Value("${zero.followers.url}")
    private String sortingUrl;

    /**
     * This method is used to call GitHub external api to find the
     * oldest accounts in GitHubRepository with Zero followers
     *
     * @return List<Items>
     */
    public UserInformation getUsersData() {
        log.info("Checking WebServiceClient.getUsersInfo() method");
        UserInformation userInformation = new UserInformation();
        try {
            log.info("Inside try block to call external api URL: {}", baseurl + sortingUrl);
            ResponseEntity<UserInformation> response = restTemplate.getForEntity(baseurl + sortingUrl
                    , UserInformation.class);
            userInformation = response.getBody();
            log.info("outside of try block");
        } catch (RuntimeException ex) {
            log.info("Runtime exception occurred", ex.getMessage());
            if (ex instanceof HttpClientErrorException.UnprocessableEntity) {
                log.info("HttpClientErrorException.UnProcessableEntity exception occurred");
                throw new ExceptionClass(ErrorConstants.ERROR_CODE_3, ErrorConstants.ERROR_RESPONSE_422, HttpStatus.UNPROCESSABLE_ENTITY);
            }
            if (ex instanceof HttpClientErrorException) {
                log.info("HttpClientErrorException occurred");
                throw new ExceptionClass(ErrorConstants.ERROR_CODE_1, ErrorConstants.ERROR_RESPONSE_404, HttpStatus.NOT_FOUND);
            }
        }
        log.info("End of WebServiceClient.getUsersInfo()");
        return userInformation;
    }
}

