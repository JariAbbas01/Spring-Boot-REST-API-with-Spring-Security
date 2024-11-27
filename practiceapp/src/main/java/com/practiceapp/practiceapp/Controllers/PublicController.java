package com.practiceapp.practiceapp.Controllers;

import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/health-Check")
    public String healthCheck(){
        return "Checking...";
    }

    @PostMapping("/create-user")
    public void saveUser(@RequestBody Users users){
        userServices.saveNewUser(users);
    }



}
