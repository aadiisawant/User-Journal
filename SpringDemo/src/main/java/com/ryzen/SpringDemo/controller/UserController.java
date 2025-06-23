package com.ryzen.SpringDemo.controller;

import com.ryzen.SpringDemo.entity.JournalEntity;
import com.ryzen.SpringDemo.entity.User;
import com.ryzen.SpringDemo.service.JournalEntryService;
import com.ryzen.SpringDemo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public String createUser(@RequestBody User user){
        userService.saveUserEntry(user);
        return "User Added.";
    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User oldUser = userService.findByUserName(user.getUsername());
        if(oldUser != null){
            oldUser.setPassword(user.getPassword());
            userService.saveUserEntry(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
