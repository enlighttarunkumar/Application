package net.engineeringdigest.journalApp.Journalservice;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repo.jrepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class journalservice {

    @Autowired
    private jrepo jerepo;
    @Autowired
    private Userservice userservice;
    @Transactional
    public void saveentry(JournalEntry entry, String username) {
        User user = userservice.findByUsername(username);
        JournalEntry saved = jerepo.save(entry);
        user.getUserJE().add(saved);
        userservice.userentry(user);
    }
    public void saveentry(JournalEntry entry) {
        jerepo.save(entry);
    }
    public List<JournalEntry> getall() {
        return jerepo.findAll();
    }
    public Optional<JournalEntry> find(ObjectId id){
        return jerepo.findById(id);
    }
    @Transactional
    public  boolean delete(ObjectId id, String username){
        boolean flag = false;
        try {
            User user = userservice.findByUsername(username);
            flag = user.getUserJE().removeIf(x -> x.getId().equals(id));
            if (flag) {
                userservice.userentry(user);
                jerepo.deleteById(id);
            }
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error while saving journal entry");
        }
        return flag;
    }

}