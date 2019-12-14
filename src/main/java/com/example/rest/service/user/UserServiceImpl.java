package com.example.rest.service.user;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getFirstName(String firstName) {
        return this.userRepository.findByFirstName(firstName);
    }

    @Override
    public boolean isExist(User user) {
        return userRepository.existsById(user.getId());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(User user) {
        userRepository.deleteById(user.getId());
    }
}
