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
@RequestMapping("/user-journal")
public class UserJournalController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @PostMapping("/addjournal/{username}")
    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity journalEntity, @PathVariable String username){
        try {
            journalEntity.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(journalEntity, username);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getjournalbyid/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId id){
        Optional<JournalEntity> journalEntity=  journalEntryService.getJournalById(id);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String username, @PathVariable ObjectId id){
        journalEntryService.deleteJournalById(id, username);
        Optional<JournalEntity> journalEntity=  journalEntryService.getJournalById(id);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{username}/{id}")
    public JournalEntity updateJournalById( @PathVariable String username,@PathVariable ObjectId id, @RequestBody JournalEntity journalUpdate){
        JournalEntity oldData = journalEntryService.getJournalById(id).orElse(null);
        if(oldData!=null){
            oldData.setTitle(journalUpdate.getTitle()!=null && !journalUpdate.getTitle().equals("") ? journalUpdate.getTitle() : oldData.getTitle());
            oldData.setContent(journalUpdate.getContent()!=null && !journalUpdate.getContent().equals("") ? journalUpdate.getContent() : oldData.getContent());
        }
        journalEntryService.saveJournalEntry(oldData);
        return oldData;
    }

    @GetMapping("/getalljournals/{username}")
    public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<JournalEntity>listOfAll = user.getJournalEntries();
        if(listOfAll!=null){
            return new ResponseEntity<>(listOfAll, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
