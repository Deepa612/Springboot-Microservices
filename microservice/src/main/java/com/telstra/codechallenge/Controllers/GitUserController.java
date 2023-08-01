package com.telstra.codechallenge.Controllers;


import com.telstra.codechallenge.DTO.Items;
import com.telstra.codechallenge.Service.GitUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
public class GitUserController {
    @Autowired
    GitUsersService gitUsersService;

    /**
     * @param number
     * @return List<Items>
     */
    @GetMapping("/retrieve/users/{number}/info")
    public ResponseEntity<List<Items>> getUsersInfo(@PathVariable("number") Integer number) {
        log.info("GitUserController.getUsersInfo():Started");
        return ResponseEntity.ok().body(this.gitUsersService.getUsersInfo(number));

    }
}
