package com.ryzen.SpringDemo.repository;

import com.ryzen.SpringDemo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    public User findByUsername(String username);
}
