package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.LoginRequestModel;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.helper.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public
    ResponseEntity<String> login(@RequestBody LoginRequestModel loginRequestModel) {
        User user = userService.findByUsername(loginRequestModel.getUsername());

        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPassword = encoder.encodeToString(loginRequestModel.getPassword().getBytes(StandardCharsets.UTF_8));

        if (!Objects.equals(user.getPassword(), encodedPassword)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        String token = TokenGenerator.generateNewToken();

        boolean success = userService.updateToken(user.getId(), token);

        if(!success){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logout(@RequestHeader("Token") String token) {
        User user = userService.findByToken(token);
        if (user == null || user.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        boolean success = userService.updateToken(user.getId(), null);
        if(!success) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

