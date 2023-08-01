package com.telstra.codechallenge.serviceimpl;


import com.telstra.codechallenge.constants.ErrorConstants;
import com.telstra.codechallenge.responsedto.Items;
import com.telstra.codechallenge.responsedto.UserInformation;
import com.telstra.codechallenge.exceptionhandling.ExceptionClass;
import com.telstra.codechallenge.service.GitHubUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitHubUsersServiceImpl implements GitHubUsersService {

    @Autowired
    WebServiceClient webServiceClient;

    /**
     * This api is used to find the oldest accounts in GitHubRepository with Zero followers
     * and return the number of account given in the @param number
     *
     * @param limit
     * @return List<Items>
     */
    @Override
    public List<Items> getUsersInfo(Integer limit) {
        log.info("Stared GitUsersServiceImpl.getUsersInfo with number value : {}", limit);
        UserInformation userInformation = webServiceClient.getUsersData();
        log.info("Checking if items list is empty");
        if (userInformation != null && !CollectionUtils.isEmpty(userInformation.getItems())) {
            log.info("Total no. of user accounts with zero followers : {}", userInformation.getItems().stream().count());
            return userInformation.getItems().stream().limit(limit).collect(Collectors.toList());
        } else {
            log.info("An exception occurred, list of item is empty");
            throw new ExceptionClass(ErrorConstants.ERROR_CODE_1, ErrorConstants.NO_ACCOUNT_FOUND_204, HttpStatus.NO_CONTENT);
        }

    }
}
