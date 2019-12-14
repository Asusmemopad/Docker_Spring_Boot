package com.example.rest.controllers;

import com.example.rest.exceptions.UserNotFoundException;
import com.example.rest.model.User;
import com.example.rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST,
                        HttpMethod.HEAD, HttpMethod.OPTIONS,
                        HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

    @GetMapping
    ResponseEntity<Collection<User>> getCollection(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<User> get(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    ResponseEntity<User> post(@RequestBody User u){
        User user = this.userService.save(new User(
                u.getFirstName(), u.getLastName()));

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.findById(id).map(u -> {
            userService.deleteById(u);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    ResponseEntity<?> head(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.findById(id)
                .map(exists -> ResponseEntity.noContent().build())
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @RequestBody User u) throws UserNotFoundException {
        return this.userService.findById(id)
                .map(existing -> {
                    User user = this.userService.save(new User(existing.getId(),
                            u.getFirstName(), u.getLastName()));
                    URI selfLink = URI.create(ServletUriComponentsBuilder
                            .fromCurrentRequest().toUriString());
                    return ResponseEntity.created(selfLink).body(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
