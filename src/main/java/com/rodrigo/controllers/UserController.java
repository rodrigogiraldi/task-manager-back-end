package com.rodrigo.controllers;

import com.rodrigo.entities.User;
import com.rodrigo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
TODO Temporary fix, must create a configuration class to enable CORS at application level
https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
*/
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
