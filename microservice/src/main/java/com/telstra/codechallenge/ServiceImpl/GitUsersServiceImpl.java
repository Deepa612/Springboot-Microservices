package com.telstra.codechallenge.ServiceImpl;


import com.telstra.codechallenge.Constants.Constants;
import com.telstra.codechallenge.DTO.Items;
import com.telstra.codechallenge.ExceptionHandling.ExceptionClass;
import com.telstra.codechallenge.Service.GitUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitUsersServiceImpl implements GitUsersService {

    @Autowired
    WebServiceClient webServiceClient;

    /**
     * This api is used to find the oldest accounts in GitHubRepository with Zero followers
     * and return the number of account given in the @param number
     *
     * @param number
     * @return List<Items>
     */
    @Override
    public List<Items> getUsersInfo(Integer number) {
        log.info("Inside GitUsersServiceImpl.getUsersInfo with number value : {}", number);
        List<Items> items = webServiceClient.getUsersData();
        if (!items.isEmpty()) {
            log.info("Inside if block to check whether the items list is empty");
            if (number <= items.size()) {
                log.info("Inside if block number is <= item list size and returned the list successfully ");
                return items.stream().limit(number).collect(Collectors.toList());
            } else {
                log.info("An exception occurred given number is > item list size");
                throw new ExceptionClass(Constants.ERROR_CODE1, Constants.Error_RESPONSE_NUMBER, HttpStatus.NOT_FOUND);
            }
        } else {
            log.info("An exception occurred list of item is empty");
            throw new ExceptionClass(Constants.ERROR_CODE1, Constants.NO_ACCOUNT_FOUND204, HttpStatus.NO_CONTENT);
        }

    }
}
