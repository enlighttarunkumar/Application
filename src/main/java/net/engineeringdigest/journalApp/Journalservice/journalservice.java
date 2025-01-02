package net.engineeringdigest.journalApp.Journalservice;

import net.engineeringdigest.journalApp.Jrepo.jrepo;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class journalservice {

    @Autowired
    private jrepo jerepo;

    public void saveentry(JournalEntry entry) {
        jerepo.save(entry);
    }
    public Optional<JournalEntry> find(ObjectId id){
        return jerepo.findById(id);
    }
    public void delete(ObjectId id){
        jerepo.deleteById(id);
    }
}
