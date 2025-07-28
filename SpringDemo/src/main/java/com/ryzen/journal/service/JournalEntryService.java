package com.ryzen.journal.service;

import com.ryzen.journal.entity.JournalEntity;
import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
        userService.saveUserJournal(user);
    }

    //method name has to be changed...
    public void saveJournalEntry(JournalEntity journalEntity){
        JournalEntity journalSaved = journalEntryRepo.save(journalEntity);
    }

    public Optional<JournalEntity> getJournalById(ObjectId Id){
       return journalEntryRepo.findById(Id);
    }

    @Transactional
    public boolean deleteJournalById(ObjectId id ,String username){
        boolean isDeleted = false;
        User user = userService.findByUserName(username);
        isDeleted = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(isDeleted) {
            userService.saveUserJournal(user);
            journalEntryRepo.deleteById(id);
        }
        return isDeleted;
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

}
