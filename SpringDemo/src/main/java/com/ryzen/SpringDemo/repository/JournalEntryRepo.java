package com.ryzen.SpringDemo.repository;

import com.ryzen.SpringDemo.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId> {
}
