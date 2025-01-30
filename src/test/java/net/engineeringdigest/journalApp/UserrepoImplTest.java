package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.repo.UserrepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserrepoImplTest {
    @Autowired
    private UserrepoImpl userrepo;
    @Test
    public void test() {
        userrepo.findUsingSentiment();
    }
}
