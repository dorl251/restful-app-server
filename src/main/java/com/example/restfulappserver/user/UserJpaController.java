package com.example.restfulappserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("User ID [%s} not found", id));
        }

        EntityModel<User> model = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-user"));

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @GetMapping("/users/{id}/posts")
    public List<UserPost> retrieveAllPostsByUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("User ID [%s} not found", id));
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<UserPost> createUserPost(@PathVariable int id, @RequestBody UserPost userPost){
        //사용자를 먼저 검색함
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("User ID [%s} not found", id));
        }
        userPost.setUser(user.get());

        //검색으로 사용자를 찾으면 savedUserPost에 넘겨준다
        UserPost savedUserPost = userPostRepository.save(userPost);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUserPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
