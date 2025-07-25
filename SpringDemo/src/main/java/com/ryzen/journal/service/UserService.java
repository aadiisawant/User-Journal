package com.ryzen.journal.service;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

@Autowired
private UserRepo userRepo;

    private static final PasswordEncoder  passwordEncoder = new BCryptPasswordEncoder();

//    public void saveUserEntry(User user){
//        userRepo.save(user);
//    }

    public void saveUserEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean saveUserTests(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public void saveUserJournal(User user){
        userRepo.save(user);
    }
    public Optional<User> getUserById(ObjectId Id){
       return userRepo.findById(Id);
    }

    public void deleteUserById(ObjectId id){
        userRepo.deleteById(id);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User findByUserName(String username){
        return userRepo.findByUsername(username);

    }
}
