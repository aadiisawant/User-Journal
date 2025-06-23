package com.ryzen.SpringDemo.service;

import com.ryzen.SpringDemo.entity.User;
import com.ryzen.SpringDemo.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

@Autowired
private UserRepo userRepo;

    public void saveUserEntry(User user){
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
