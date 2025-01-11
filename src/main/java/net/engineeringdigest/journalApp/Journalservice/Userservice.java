package net.engineeringdigest.journalApp.Journalservice;

import net.engineeringdigest.journalApp.repo.Userrepo;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Userservice {

    @Autowired
    private Userrepo userrepo;

    public void userentry(User entry) {
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