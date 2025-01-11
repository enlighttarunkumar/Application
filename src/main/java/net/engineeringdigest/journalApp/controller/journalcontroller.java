package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.Journalservice.journalservice;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
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
    @Autowired
    private Userservice userservice;
    @GetMapping("{username}")
    public ResponseEntity<?>getall(@PathVariable String username) {
        User user = userservice.findByUsername(username);
        List<JournalEntry> all = user.getUserJE();
        if(all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{username}")
    public ResponseEntity<?> create(@RequestBody JournalEntry entry,@PathVariable String username) {
        try {
            entry.setDate(LocalDateTime.now());
            jservice.saveentry(entry,username);
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
    @DeleteMapping("id/{username}/{myid}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myid,@PathVariable String username){
        jservice.delete(myid,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @PutMapping("id/{myid}")
//    public ResponseEntity<?> update(@PathVariable ObjectId myid,@RequestBody JournalEntry entry){
//        JournalEntry old = jservice.find(myid).orElse(null);
//        if(old != null){
//            old.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
//            old.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
//            jservice.saveentry(old, username);
//            return new ResponseEntity<>(old, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}