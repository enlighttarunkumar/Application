package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.Journalservice.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void sendEmail() {
        emailService.sendEmail("","USING MAIL FIRST TIME", "I THINK I CAN DO IT");
    }
}
