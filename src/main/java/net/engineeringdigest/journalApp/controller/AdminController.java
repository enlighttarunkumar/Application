package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private Userservice userservice;
    @GetMapping("/all-user")
    public ResponseEntity<?>getAllUsers() {
        List<User>all = userservice.getall();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public ResponseEntity<?>createAdminUser(@RequestBody User user) {
        userservice.adminentry(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
