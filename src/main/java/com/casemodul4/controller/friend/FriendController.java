package com.casemodul4.controller;

import com.casemodul4.service.friend.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    IFriendService iFriendService;


    @GetMapping

    @PostMapping

    @PutMapping

    @DeleteMapping
}
