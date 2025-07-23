package com.ryzen.journal.controller;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.UserRepo;
import com.ryzen.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User oldUser = userService.findByUserName(userName);
        if(oldUser != null){
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            userService.saveUserEntry(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepo.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
