package com.ryzen.SpringDemo.service;

import com.ryzen.SpringDemo.entity.JournalEntity;
import com.ryzen.SpringDemo.entity.User;
import com.ryzen.SpringDemo.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntity journalEntity, String username){
        User user = userService.findByUserName(username);
        JournalEntity journalSaved = journalEntryRepo.save(journalEntity);
        user.getJournalEntries().add(journalSaved);
        userService.saveUserEntry(user);
    }

    public void saveJournalEntry(JournalEntity journalEntity){
        JournalEntity journalSaved = journalEntryRepo.save(journalEntity);
    }

    public Optional<JournalEntity> getJournalById(ObjectId Id){
       return journalEntryRepo.findById(Id);
    }

    public void deleteJournalById(ObjectId id ,String username){
        User user = userService.findByUserName(username);
        System.out.println(user);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalEntryRepo.deleteById(id);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

}
