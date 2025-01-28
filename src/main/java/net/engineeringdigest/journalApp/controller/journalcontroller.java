package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.Journalservice.journalservice;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal123")
@Slf4j
public class journalcontroller {

    @Autowired
    private journalservice jservice;
    @Autowired
    private Userservice userservice;

    @GetMapping
    public ResponseEntity<?>getall() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservice.findByUsername(username);
        List<JournalEntry> all = user.getUserJE();
        if(all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            entry.setDate(LocalDateTime.now());
            String username = authentication.getName();
            jservice.saveentry(entry,authentication.getName());
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("error",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("id/{myid}")
    public ResponseEntity<?> findById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservice.findByUsername(username);
        List<JournalEntry>collect = user.getUserJE().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntry> obj = jservice.find(myid);
            if (obj.isPresent()) {
                return new ResponseEntity<>(obj.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean check = jservice.delete(myid, username);
        if(check) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myid}")
    public ResponseEntity<?> update(@PathVariable ObjectId myid,@RequestBody JournalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservice.findByUsername(username);
        List<JournalEntry>collect = user.getUserJE().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntry> obj = jservice.find(myid);
            if (obj.isPresent()) {
                JournalEntry old = obj.get();
                old.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
                old.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
                jservice.saveentry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}