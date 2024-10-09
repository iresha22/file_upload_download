package com.develhope.file_upload_exercise.controllers;

import com.develhope.file_upload_exercise.entities.User;
import com.develhope.file_upload_exercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    // parameters
    @Autowired
    private UserRepository userRepository;
    //Create user
    @PostMapping("/new")
    public ResponseEntity<User> newUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    //Get one user
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUser(@PathVariable Long idUser){
        Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }
    // Get list of Users
    @GetMapping("/userList")
    public ResponseEntity<List<User>> userList(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User updateUser = userOptional.get();
            updateUser.setName(user.getName());
            updateUser.setAge(user.getAge());
            return ResponseEntity.ok(userRepository.save(updateUser));
        }
        return ResponseEntity.notFound().build();
    }
    // Delete one user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    // delete all the users
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers(){
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

}
