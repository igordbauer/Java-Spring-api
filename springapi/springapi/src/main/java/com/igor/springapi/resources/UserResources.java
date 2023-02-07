package com.igor.springapi.resources;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.igor.springapi.domain.User;
import com.igor.springapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET) // ou @Getmapping
    public ResponseEntity<List<User>> findAll() {
        // User maria = new User("1", "Maria Silva", "maria@gmail.com");
        // User alex = new User("2", "Alex Silva", "alex@gmail.com");
        List<User> list = service.findAll();
        // list.addAll(Arrays.asList(maria, alex));

        return ResponseEntity.ok().body(list);
    }

}
