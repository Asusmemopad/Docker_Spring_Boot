package com.example.rest;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class RestApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            User user0 = User.builder()
                    .firstName("Tom").lastName("Holand").build();
            User user1 = User.builder()
                    .firstName("Andrey").lastName("Viliamson").build();
            User user2 = User.builder()
                    .firstName("Petro").lastName("Revild").build();

            Stream.of(user0, user1, user2)
                    .forEach(n -> userRepository.save(n));
        };
    }
}
