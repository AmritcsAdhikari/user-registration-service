package com.app.registration.controller;

import com.app.registration.dto.UserDTO;
import com.app.registration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserDTO> CreateUser(@Valid @RequestBody UserDTO userDTO){

        return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }
}
