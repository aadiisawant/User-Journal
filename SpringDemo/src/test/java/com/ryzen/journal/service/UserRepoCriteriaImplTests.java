package com.ryzen.journal.service;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.UserRepoCriteriaImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepoCriteriaImplTests {

    @Autowired
    private UserRepoCriteriaImpl userRepoCriteria;

    @Test
    public void getUsers(){
        System.out.println(userRepoCriteria.getUserForSA());
    }
}
