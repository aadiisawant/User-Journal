//package com.ryzen.SpringDemo.controller;
//
//import com.ryzen.SpringDemo.entity.JournalEntity;
//import com.ryzen.SpringDemo.service.JournalEntryService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalController {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    @PostMapping("/addjournal")
//    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity journalEntity){
//        try {
//            journalEntity.setDate(LocalDateTime.now());
////            journalEntryService.saveJournalEntry(journalEntity);
//            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
//        }catch(Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/getjournalbyid/{id}")
//    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId id){
//        Optional<JournalEntity> journalEntity=  journalEntryService.getJournalById(id);
//        if(journalEntity.isPresent()){
//            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id){
////        journalEntryService.deleteJournalById(id);
//        Optional<JournalEntity> journalEntity=  journalEntryService.getJournalById(id);
//        if(journalEntity.isPresent()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/update/{id}")
//    public JournalEntity updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntity journalUpdate){
//        JournalEntity oldData = journalEntryService.getJournalById(id).orElse(null);
//        if(oldData!=null){
//            oldData.setTitle(journalUpdate.getTitle()!=null && !journalUpdate.getTitle().equals("") ? journalUpdate.getTitle() : oldData.getTitle());
//            oldData.setContent(journalUpdate.getContent()!=null && !journalUpdate.getContent().equals("") ? journalUpdate.getContent() : oldData.getContent());
//        }
////        journalEntryService.saveJournalEntry(oldData, user);
//        return oldData;
//    }
//
//    @GetMapping("/getalljournals")
//    public ResponseEntity<?> getAllJournals(){
//        List<JournalEntity>listOfAll =  journalEntryService.getAll();
//        if(!listOfAll.isEmpty()){
//            return new ResponseEntity<>(listOfAll, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}
