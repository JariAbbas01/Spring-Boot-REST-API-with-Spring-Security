package com.practiceapp.practiceapp.Services;


import com.practiceapp.practiceapp.Entity.JournalEntry;
import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired UserServices userServices;

//    Post Logic

    @Transactional
    public void createEntry(JournalEntry journalEntry, String userName){
        journalEntry.setDate(LocalDateTime.now());
        Users user = userServices.findByUserName(userName);
        JournalEntry saved = journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);

        userServices.saveUser(user);

    }

    public void createEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalRepository.save(journalEntry);
    }

//    Get All

    public List<JournalEntry> getAll(String userName){
        Users user = userServices.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        return journalEntries;
    }

    public List<JournalEntry> getAll(){
        List<JournalEntry> all = journalRepository.findAll();
        return all;
    }

//    Get Entry By ID

    public Optional<JournalEntry> getEntryById(ObjectId myId, String userName){
        Users user = userServices.findByUserName(userName);

        List<JournalEntry> journalEntries = user.getJournalEntries();

        for (JournalEntry entry : journalEntries){
            if(entry.getId().equals(myId)){
                Optional<JournalEntry> byId = journalRepository.findById(myId);
                return byId;
            }
        }

        return Optional.empty();

    }

//    Delete Entry By ID

    @Transactional
    public void deleteEntryById(ObjectId myId, String userName){
        Users user = userServices.findByUserName(userName);

        boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));

        if(removed){
            journalRepository.deleteById(myId);
            userServices.saveUser(user);
        }


    }









}
