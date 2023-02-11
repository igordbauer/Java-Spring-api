package com.igor.springapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.igor.springapi.DTO.UserDTO;
import com.igor.springapi.domain.Post;
import com.igor.springapi.domain.User;
import com.igor.springapi.services.UserService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserResources { // UserControllers

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET) // ou @Getmapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // ou @Getmapping
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // ou @Getmapping
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST) // ou @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto) {
        User user = service.fromDTO(objDto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // ou @PostMapping
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {

        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET) // ou @Getmapping
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = service.findById(id);
        List<Post> posts = obj.getPosts();
        return ResponseEntity.ok().body(posts);
    }

}
