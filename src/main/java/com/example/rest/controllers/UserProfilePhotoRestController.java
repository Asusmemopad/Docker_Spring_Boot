package com.example.rest.controllers;

import com.example.rest.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
@RequestMapping(value = "/users/{id}/photo")
@Log4j2
public class UserProfilePhotoRestController {

    private File root;
    private final UserService userService;

    @Autowired
    public UserProfilePhotoRestController(
            @Value("${upload.dir:${user.home}/images}") String uploadDir,
                                          UserService userService) {
        this.root = new File(uploadDir);
        this.userService = userService;
    }
}
