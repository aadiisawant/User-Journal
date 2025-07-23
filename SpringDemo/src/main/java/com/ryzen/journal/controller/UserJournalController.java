package com.ryzen.journal.controller;

import com.ryzen.journal.entity.JournalEntity;
import com.ryzen.journal.entity.User;
import com.ryzen.journal.service.JournalEntryService;
import com.ryzen.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-journal")
public class UserJournalController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
  
    @PostMapping("/addjournal")
    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity journalEntity){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            journalEntity.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(journalEntity, authentication.getName());
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getjournalbyid/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntity> jEntries = user.getJournalEntries().stream().filter(data -> data.getId().equals(id)).collect(Collectors.toList());
        if(!jEntries.isEmpty()){
            Optional<JournalEntity> journalEntity=  journalEntryService.getJournalById(id);
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean deleted = journalEntryService.deleteJournalById(id, userName);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntity journalUpdate){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntity> jEntries = user.getJournalEntries().stream().filter(data -> data.getId().equals(id)).collect(Collectors.toList());
        if(!jEntries.isEmpty()){
            JournalEntity oldData = journalEntryService.getJournalById(id).orElse(null);
            if(oldData!=null){
                oldData.setTitle(journalUpdate.getTitle()!=null && !journalUpdate.getTitle().equals("") ? journalUpdate.getTitle() : oldData.getTitle());
                oldData.setContent(journalUpdate.getContent()!=null && !journalUpdate.getContent().equals("") ? journalUpdate.getContent() : oldData.getContent());
            }
            journalEntryService.saveJournalEntry(oldData);
            return new ResponseEntity<>(journalEntryService.getJournalById(id).orElse(null), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getalljournals")
    public ResponseEntity<?> getAllJournalEntriesofUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntity>listOfAll = user.getJournalEntries();
        if(listOfAll!=null && !listOfAll.isEmpty()){
            return new ResponseEntity<>(listOfAll, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
