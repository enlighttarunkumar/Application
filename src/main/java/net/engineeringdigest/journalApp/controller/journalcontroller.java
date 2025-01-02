package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.journalservice;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal123")
public class journalcontroller {

    @Autowired
    private journalservice jservice;

    @GetMapping
    public List<JournalEntry>getall(){
        return null;
    }
    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        jservice.saveentry(entry);
        return entry;
    }
    @GetMapping("id/{myid}")
    public JournalEntry findById(@PathVariable ObjectId myid){
        return jservice.find(myid).orElse(null);
    }
    @DeleteMapping("id/{myid}")
    public boolean delete(@PathVariable ObjectId myid){
        jservice.delete(myid);
        return true;
    }
    @PutMapping("id/{myid}")
    public JournalEntry update(@PathVariable ObjectId myid,@RequestBody JournalEntry entry){
        JournalEntry old = jservice.find(myid).orElse(null);
        if(old != null){
            old.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
            old.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            jservice.saveentry(old);
        }
        return old;
    }
}
