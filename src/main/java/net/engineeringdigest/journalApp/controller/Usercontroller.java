package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.Journalservice.WeatherService;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user123")
public class Usercontroller {

    @Autowired
    private Userservice userservice;

    @Autowired
    private Userrepo userrepo;

    @Autowired
    private WeatherService weatherservice;

    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User newUser){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User objj = userservice.findByUsername(username);
        objj.setUsername(newUser.getUsername());
        objj.setPassword(newUser.getPassword());
        userservice.newentry(objj);
        return new ResponseEntity<>(objj,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteuser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userrepo.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherservice.getWeather("Mumbai");
        String greetings = "";
        if(response!=null){
            greetings = ", Weather feels like " + response.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("HI " + authentication.getName() + greetings,HttpStatus.CREATED);
    }


}