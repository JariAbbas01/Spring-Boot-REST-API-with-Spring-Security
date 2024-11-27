package com.practiceapp.practiceapp.Controllers;


import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;


    @PostMapping("/create-user")
    public void saveUser(@RequestBody Users users){
        userServices.saveAdminUser(users);
    }


//    Get All Users

    @GetMapping("/all-users")
    public ResponseEntity<?> getUsers(){
        List<Users> allUsers = userServices.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }




}
