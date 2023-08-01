package com.telstra.codechallenge.service;


import com.telstra.codechallenge.responsedto.Items;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GitHubUsersService {
    List<Items> getUsersInfo(@PathVariable("limit") Integer limit);
}
