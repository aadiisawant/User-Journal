package com.ryzen.journal.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class JavaMailServiceTests {

    @Autowired
    private JavaMailService javaMailService;

    @Test
    public void sendMail(){
        javaMailService.sendMail("Receiver's Email","JAVA Mail Service Test-2.", "This is an test mail from User-Journal App. Thank you!");
    }
}
