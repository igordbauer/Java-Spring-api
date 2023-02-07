package com.igor.springapi.resources;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.igor.springapi.DTO.UserDTO;
import com.igor.springapi.domain.User;
import com.igor.springapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET) // ou @Getmapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

}
