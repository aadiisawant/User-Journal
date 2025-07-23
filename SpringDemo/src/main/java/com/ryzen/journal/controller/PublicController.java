package com.ryzen.journal.controller;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @PostMapping("/createuser")
    public String createUser(@RequestBody User user){
        userService.saveUserEntry(user);
        return "User Created.";
    }
}
