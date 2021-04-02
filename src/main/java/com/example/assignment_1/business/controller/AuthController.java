package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.User;
import com.example.assignment_1.data.model.UserDB;
import com.example.assignment_1.data.model.UserRole;
import com.example.assignment_1.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

class LoginRequestModel {
    String username;
    String password;
}

@RestController("/auth")
public class AuthController {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody LoginRequestModel loginRequestModel) {
        UserDB user =  userRepository.findByUsername(loginRequestModel.username);

        if (Objects.equals(user.getPassword(), loginRequestModel.password)) {
            return user.getToken();
        }

        return null;
    }

    @PostMapping("/create")
    public ResponseEntity.BodyBuilder create(@RequestBody User user, @RequestHeader("Token") String token) {
        UserDB requestingUser =  userRepository.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        userRepository.save(new UserDB(user));

        return ResponseEntity.status(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long id, @RequestHeader("Token") String token) {
        UserDB requestingUser =  userRepository.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.CREATED);
    }

}
