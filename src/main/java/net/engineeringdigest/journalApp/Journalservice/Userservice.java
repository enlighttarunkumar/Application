package net.engineeringdigest.journalApp.Journalservice;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.repo.Userrepo;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class Userservice {

    @Autowired
    private Userrepo userrepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void userentry(User entry) {
        userrepo.save(entry);
    }
    public void newentry(User entry) {
        try {
            entry.setPassword(passwordEncoder.encode(entry.getPassword()));
            entry.setRoles(Arrays.asList("USER"));
            userrepo.save(entry);
        }
        catch (Exception e) {
            log.error("error",e);
            throw new RuntimeException(e);
        }
    }
    public void adminentry(User entry) {
        entry.setPassword(passwordEncoder.encode(entry.getPassword()));
        entry.setRoles(Arrays.asList("USER","ADMIN"));
        userrepo.save(entry);
    }
    public List<User> getall() {
        return userrepo.findAll();
    }
    public Optional<User> find(ObjectId id){
        return userrepo.findById(id);
    }
    public void delete(ObjectId id){
        userrepo.deleteById(id);
    }
    public User findByUsername(String Username) {
        return userrepo.findByUsername(Username);
    }
}