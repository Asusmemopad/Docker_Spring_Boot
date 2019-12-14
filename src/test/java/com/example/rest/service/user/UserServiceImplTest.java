package com.example.rest.service.user;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        User tom = new User();
        tom.setFirstName("Tom");

        Mockito.when(userRepository.findByFirstName(tom.getFirstName()))
                .thenReturn(tom);
    }

    @Test
    public void findByFirstName() {
        String name = "Tom";
        User found = userService.getFirstName(name);

        assertThat(found.getFirstName())
                .isEqualTo(name);
    }
}