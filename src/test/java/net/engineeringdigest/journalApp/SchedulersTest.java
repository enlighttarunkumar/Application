package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.Schedulers.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchedulersTest {
    @Autowired
    private UserScheduler userScheduler;
    @Test
    public void test() {
        userScheduler.fetchUserAndSendEmail();
    }
}
