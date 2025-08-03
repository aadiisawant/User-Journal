package com.ryzen.journal.repository;

import com.ryzen.journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepoCriteriaImpl {

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("aditya"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
