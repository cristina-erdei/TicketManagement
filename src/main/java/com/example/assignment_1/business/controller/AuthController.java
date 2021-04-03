package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.LoginRequestModel;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.business.service.interfaces.UserService;
import com.example.assignment_1.data.model.UserDB;
import com.example.assignment_1.data.model.UserRole;
import com.example.assignment_1.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

@RestController("/authentication")
public class AuthController {


    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody LoginRequestModel loginRequestModel) {
        User user =  userService.findByUsername(loginRequestModel.getUsername());

        if (!Objects.equals(user.getPassword(), loginRequestModel.getPassword())) {
            return null;
        }

        String token = tokenGenerationStrategy();

        userService.updateToken(user.getId(), token);

        return token;
    }

    private String tokenGenerationStrategy() {

        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = (int)
                    (random.nextFloat() * 255);
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}

