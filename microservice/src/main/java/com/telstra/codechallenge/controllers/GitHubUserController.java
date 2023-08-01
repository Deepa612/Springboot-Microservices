package com.telstra.codechallenge.controllers;


import com.telstra.codechallenge.responsedto.Items;
import com.telstra.codechallenge.service.GitHubUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Slf4j
@Validated
@RequestMapping("v1")
public class GitHubUserController {
    @Autowired
    GitHubUsersService gitUsersService;

    /**
     * This method is used to call GitHub external api to find the
     * oldest accounts in GitHubRepository with Zero followers
     *
     * @param limit
     * @return List<Item>
     */

    @GetMapping("/zero-followers/userAccounts/{limit}")
    public ResponseEntity<List<Items>> getUsersInfo(@PathVariable("limit") @Min(value = 1) Integer limit) {
        log.info("GitUserController.getUsersInfo():Started");
        return ResponseEntity.ok().body(this.gitUsersService.getUsersInfo(limit));

    }
}
