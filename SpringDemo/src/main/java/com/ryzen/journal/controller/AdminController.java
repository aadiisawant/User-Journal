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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/get-all-users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/get-all-journals")
    public ResponseEntity<?> getAllJournalEntriesFromDB(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntity>listOfAll = journalEntryService.getAll();
        if(listOfAll!=null && !listOfAll.isEmpty()){
            return new ResponseEntity<>(listOfAll, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
