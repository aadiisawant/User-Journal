package com.ryzen.journal.controller;

import com.ryzen.journal.entity.JournalEntity;
import com.ryzen.journal.entity.User;
import com.ryzen.journal.service.JournalEntryService;
import com.ryzen.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create-admin")
    public String createAdmin(@RequestBody User user){
        userService.saveNewAdmin(user);
        return "Admin Created.";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        boolean isCreated = false;
        isCreated = userService.saveUserEntry(user);
        if(isCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Username already exists",HttpStatus.CONFLICT);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?>  getAll(){

        List<User> users = userService.getAll();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get-all-journals")
    public ResponseEntity<?> getAllJournalEntriesFromDB(){

        List<JournalEntity> journals = journalEntryService.getAll();
        if(journals!=null && !journals.isEmpty()){
            return new ResponseEntity<>(journals, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
