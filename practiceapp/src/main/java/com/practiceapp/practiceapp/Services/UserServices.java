package com.practiceapp.practiceapp.Services;

import com.practiceapp.practiceapp.Config.EncoderConfig;
import com.practiceapp.practiceapp.Entity.Users;
import com.practiceapp.practiceapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncoderConfig encoderConfig;

//    Post

    public void saveNewUser(Users users){
        users.setPassword(encoderConfig.passwordEncoder().encode(users.getPassword()));
        users.setRoles(Arrays.asList("USER"));
        userRepository.save(users);
    }

    public void saveAdminUser(Users users){
        users.setPassword(encoderConfig.passwordEncoder().encode(users.getPassword()));
        users.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(users);
    }

    public void saveUser(Users users){
        users.setRoles(Arrays.asList("USER"));
        userRepository.save(users);
    }

//    Get All Users

    public List<Users> getAllUsers(){
        List<Users> usersEntry = userRepository.findAll();
        return usersEntry;
    }

//    Get User by UserName

    public Users findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

//    Delete User by UserName

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }







}
