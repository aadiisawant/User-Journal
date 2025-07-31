package com.ryzen.journal.service;

import com.ryzen.journal.pojo.DummyUser;
import com.ryzen.journal.pojo.DummyUserResponse;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DummyUsersAPI {

    private String API = "https://dummyjson.com/users";

    @Autowired
    RestTemplate restTemplate;

    List<DummyUser> users;

    public void getUsers(){
        ResponseEntity<DummyUserResponse> response = restTemplate.exchange(API, HttpMethod.GET, null , DummyUserResponse.class );
        DummyUserResponse dummyUserResponse = response.getBody();
        if (dummyUserResponse != null && dummyUserResponse.getUsers() != null) {
            List<DummyUser> users = dummyUserResponse.getUsers();
            users.forEach(user -> System.out.println(user.getId() + " - " + user.getFirstName()));
        } else {
            System.out.println("No users found or response is null");
        }

    }
}
