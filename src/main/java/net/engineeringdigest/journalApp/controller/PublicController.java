package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private Userservice userservice;

    @GetMapping("/health-check")
    public String healthcheck() {
        return "OK";
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        userservice.newentry(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
