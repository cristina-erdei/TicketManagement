package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.LoginRequestModel;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping("/authentication")
public class AuthController {


    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public @ResponseBody
    String login(@RequestBody LoginRequestModel loginRequestModel) {
        User user = userService.findByUsername(loginRequestModel.getUsername());

        if (!Objects.equals(user.getPassword(), loginRequestModel.getPassword())) {
            return null;
        }

        String token = generateNewToken();

        userService.updateToken(user.getId(), token);

        return token;
    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    private String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Token") String token) {
        User user = userService.findByToken(token);
        if (user == null || user.getId() == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        System.out.println("userService.updateToken(user.getId(), null) = " + userService.updateToken(user.getId(), null));
        var result =new ResponseEntity(HttpStatus.OK);
        System.out.println("result = " + result);

        return result;

    }
}

