package com.ryzen.journal.controller;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.UserRepo;
import com.ryzen.journal.service.DummyUsersAPI;
import com.ryzen.journal.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private DummyUsersAPI dummyUsersAPI;
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(!userRepo.existsByUsername(user.getUsername())){
            userService.saveUserEntry(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Value("${app.user.name}")
    String name;
    @Value("${app.user.city}")
    String city;

    @GetMapping
    public String getUserNCity(){
        return "I'm "+name+" from "+city+".";
    }

    @GetMapping("/getusers")
    public String getUsers(){
        dummyUsersAPI.getUsers();
        return "Fetched...";
    }
}
