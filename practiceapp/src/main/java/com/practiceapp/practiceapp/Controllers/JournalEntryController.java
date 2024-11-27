package com.practiceapp.practiceapp.Controllers;


import com.practiceapp.practiceapp.Entity.JournalEntry;
import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;



//    Post

    @PostMapping()
    public Boolean createEntry(@RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        journalEntryServices.createEntry(journalEntry, userName);
        return true;
    }

//    Get All

    @GetMapping()
    public ResponseEntity<?> getEntry(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<JournalEntry> entry = journalEntryServices.getAll(userName);
        return new ResponseEntity<>(entry,HttpStatus.OK);
    }

//    Get Entry By ID

    @GetMapping("id/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<JournalEntry> entryById = journalEntryServices.getEntryById(id, userName);
        if(entryById != null && !entryById.isEmpty()){
            return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

//    Delete Entry By ID

    @DeleteMapping("id/{id}")
    public Boolean deleteEntryById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryServices.deleteEntryById(id, userName);
        return true;
    }

//    Update Entry By ID



    @PutMapping("id/{id}")
    public void updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<JournalEntry> entryById = journalEntryServices.getEntryById(id, userName);
        JournalEntry existingEntry = entryById.get();

        if(existingEntry != null){
            existingEntry.setTitle(journalEntry.getTitle());
            existingEntry.setContent(journalEntry.getContent());
            journalEntryServices.createEntry(existingEntry);
        }


    }

}
