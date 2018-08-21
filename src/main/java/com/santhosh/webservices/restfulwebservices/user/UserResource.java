package com.santhosh.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(value = "/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {

        User savedUser = null;
        try {
            savedUser = userDaoService.findOne(id);
        }catch(Exception e) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<User>(savedUser);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable int id) {

        User user = userDaoService.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }
}
