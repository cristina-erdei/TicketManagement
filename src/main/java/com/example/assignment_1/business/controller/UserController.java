package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.helper.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> findAll(@RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findById(userId);

        if (user == null || user.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/getAllByRole/{role}")
    public ResponseEntity<List<User>> findAllByRole(@PathVariable UserRole role, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userService.findAllByRole(role), HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null || user.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/getByToken/{token}")
    public ResponseEntity<User> findByToken(@PathVariable String token) {
        User user = userService.findByToken(token);

        if (user == null || user.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User newValue, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        boolean successful = userService.update(id, newValue);

        if (!successful) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateToken/{id}")
    public ResponseEntity updateToken(@PathVariable Long id, @RequestBody String token) {
        boolean successful = userService.updateToken(id, token);
        if (!successful) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity deleteById(@PathVariable Long userId, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findById(userId);
        if(user == null || user.getId() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        userService.deleteById(userId);
        return new ResponseEntity(HttpStatus.OK);

    }

}
