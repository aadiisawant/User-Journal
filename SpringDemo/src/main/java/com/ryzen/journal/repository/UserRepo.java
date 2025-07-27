package com.ryzen.journal.repository;

import com.ryzen.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    public User findByUsername(String username);
    public boolean existsByUsername(String username);
    public void deleteByUsername(String username);
}
