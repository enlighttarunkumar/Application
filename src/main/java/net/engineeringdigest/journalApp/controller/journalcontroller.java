package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.journalservice;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal123")
public class journalcontroller {

    @Autowired
    private journalservice jservice;

    @GetMapping
    public ResponseEntity<?>getall(){
        List<JournalEntry> all = jservice.getall();
        if(all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody JournalEntry entry){
        try {
            entry.setDate(LocalDateTime.now());
            jservice.saveentry(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("id/{myid}")
    public ResponseEntity<?> findById(@PathVariable ObjectId myid){

        Optional<JournalEntry> obj = jservice.find(myid);
        if(obj.isPresent()){
            return new ResponseEntity<>(obj.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myid){
        jservice.delete(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{myid}")
    public ResponseEntity<?> update(@PathVariable ObjectId myid,@RequestBody JournalEntry entry){
        JournalEntry old = jservice.find(myid).orElse(null);
        if(old != null){
            old.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
            old.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            jservice.saveentry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}