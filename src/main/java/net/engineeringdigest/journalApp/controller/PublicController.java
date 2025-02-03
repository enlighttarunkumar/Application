package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Journalservice.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.Journalservice.Userservice;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private Userservice userservice;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtutil;

    @GetMapping("/health-check")
    public String healthcheck() {
        return "OK";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        userservice.newentry(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User newUser){
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
            String jwt = jwtutil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }
        catch(Exception e){
            log.error("ERROR while trying to login");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
