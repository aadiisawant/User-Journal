package com.ryzen.journal.repository;

import com.ryzen.journal.entity.DummyAPIConfigEntity;
import com.ryzen.journal.pojo.DummyUserResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyUserAPIRepo extends MongoRepository<DummyAPIConfigEntity, ObjectId> {

}
