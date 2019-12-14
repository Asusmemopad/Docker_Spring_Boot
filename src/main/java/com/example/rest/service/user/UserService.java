package com.example.rest.service.user;

import com.example.rest.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Collection<User> findAll();
    Optional<User> findById(Long id);
    User getFirstName(String firstName);
    boolean isExist(User user);
    User save(User user);
    void deleteById(User user);
}
