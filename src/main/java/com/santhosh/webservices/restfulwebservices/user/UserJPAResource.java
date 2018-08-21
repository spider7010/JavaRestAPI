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
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

//    @Autowired
//    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {

       Optional<User> optional = userRepository.findById(id);
           if(!optional.isPresent())
               throw new UserNotFoundException("id-" + id);
        Resource<User> resource = new Resource<User>(optional.get());
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @GetMapping(value = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
       userRepository.deleteById(id);
    }

    @GetMapping(value = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) {
        Optional<User> optionalUser=userRepository.findById(id);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("id-"+id);
        return optionalUser.get().getPostList();
    }

    @PostMapping(value = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> optionalUser=userRepository.findById(id);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("id-"+id);
        User user = optionalUser.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
