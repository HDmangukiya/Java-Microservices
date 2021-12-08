package com.userdetails.controller;

import com.userdetails.modal.UserEntity;
import com.userdetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getusercontacts")
    ResponseEntity<UserEntity> getUser(@RequestParam(name = "id", required = false, defaultValue = "0") int id, @RequestParam(name = "username", required = false) String username) throws Exception {
        if (id == 0 && username == null) {
            throw new Exception("Either id or username parameter is required!!");
        }
        return new ResponseEntity<UserEntity>(userService.getUserContact(id, username), HttpStatus.OK);
    }
}
