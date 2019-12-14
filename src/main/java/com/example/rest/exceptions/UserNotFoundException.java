package com.example.rest.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        System.out.println("User with " + id + " not found");
    }
}
