package com.telstra.codechallenge.Service;


import com.telstra.codechallenge.DTO.Items;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GitUsersService {
    List<Items> getUsersInfo(@PathVariable("number") Integer number);
}
