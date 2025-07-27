package com.ryzen.journal.service;

import com.ryzen.journal.entity.User;
import com.ryzen.journal.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;
    @Test
    public void addvalues(){
        assertEquals(4, 1+3);
    }

    @ParameterizedTest
    @CsvSource({
            "aditya",
            "ram",
            "raju"
    })
    public void testFindByUsername(String username){
        assertNotNull(userRepo.findByUsername(username));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(CreateNewUserArgs.class)
    public void addUser(User user){
        assertTrue(userService.saveUserTests(user));
    }
}
