package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user123")
public class Usercontroller {

    @Autowired
    private Userservice userservice;

    @GetMapping
    public List<User>getAllUsers(){
        return userservice.getall();
    }
    @PostMapping
    public ResponseEntity<?>createUser(@RequestBody User newUser){
        userservice.userentry(newUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{Username}")
    public ResponseEntity<?> updateuser(@RequestBody User newUser, @PathVariable String Username){
        User objj = userservice.findByUsername(Username);
        if(objj != null) {
            objj.setUsername(newUser.getUsername());
            objj.setPassword(newUser.getPassword());
            userservice.userentry(objj);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
