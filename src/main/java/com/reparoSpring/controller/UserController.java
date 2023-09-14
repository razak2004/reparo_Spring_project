package com.reparoSpring.controller;

import com.reparoSpring.dto.user.UserRequestDto;
import com.reparoSpring.dto.user.UserResponseDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {
@Autowired
private  UserService userService ;
    @PostMapping("/createUser")
    public ResponseEntity<String> createResource(@RequestBody UserRequestDto request) {
        try {
          int id = userService.createUser(request);
            return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            // If a ServiceException occurs, return an error response with a 400 Bad Request status code
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @GetMapping("/findByNum")
    public ResponseEntity<String>getUserByNumber(@RequestParam("number") long number){
        try {
            UserResponseDto resp = userService.findUserByNumber(number);
            return ResponseEntity.ok(resp.toString());
        } catch (ServiceException e) {
            // If a ServiceException occurs, return an error response with a 400 Bad Request status code
            return ResponseEntity.ok(e.getMessage());
        }

    }

}
