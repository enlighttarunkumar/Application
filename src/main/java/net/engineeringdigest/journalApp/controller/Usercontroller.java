package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user123")
public class Usercontroller {

    @Autowired
    private Userservice usservice;
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
    @PutMapping("/{username}")
    public ResponseEntity<?> update(@RequestBody User newUser, @PathVariable String username){
        User obj = userservice.findByUsername(username);
        if(obj != null){
            obj.setUsername(newUser.getUsername());
            obj.setPassword(newUser.getPassword());
        }
        userservice.userentry(newUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
