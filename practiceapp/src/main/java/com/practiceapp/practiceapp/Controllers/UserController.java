package com.practiceapp.practiceapp.Controllers;

import com.practiceapp.practiceapp.Entity.JournalEntry;
import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Repository.JournalRepository;
import com.practiceapp.practiceapp.Repository.UserRepository;
import com.practiceapp.practiceapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JournalRepository journalRepository;

//    Post




//    Get User by UserName

    @GetMapping
    public ResponseEntity<?> getUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users byUserName = userServices.findByUserName(userName);
        return new ResponseEntity<>(byUserName, HttpStatus.OK);
    }

    @DeleteMapping

    public Boolean deleteByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Users user = userServices.findByUserName(userName);

        List<JournalEntry> journalEntries = user.getJournalEntries();

        for(JournalEntry entry : journalEntries){
            journalRepository.deleteById(entry.getId());
        }
        userServices.deleteByUserName(userName);
        return null;
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody Users users){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userServices.findByUserName(userName);
        if(user != null){
            user.setUserName(users.getUserName());
            user.setPassword(users.getPassword());
            userServices.saveNewUser(user);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
