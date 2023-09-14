package com.reparoSpring.service;


import com.reparoSpring.dto.user.UserRequestDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {
@Autowired
private UserRepository userRepository;
@Autowired
private  UserService userService;
    @Test
    void saveUser(){
        UserRequestDto use = new UserRequestDto("abdul",9789068945L,"abd123",2);
        try {
           int id = userService.createUser(use);
            assertNotNull(userRepository.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}