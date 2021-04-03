package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.data.model.UserRole;
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
    public @ResponseBody
    ResponseEntity<List<User>> findAll(@RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public @ResponseBody
    ResponseEntity<User> findById(@PathVariable Long userId, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/getAllByRole/{role}")
    public @ResponseBody
    ResponseEntity<List<User>> findAllByRole(@PathVariable UserRole role, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userService.findAllByRole(role), HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public @ResponseBody
    User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/getByToken/{token}")
    public @ResponseBody
    User findByToken(@PathVariable String token) {
        return userService.findByToken(token);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity.BodyBuilder save(User user, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        userService.save(user);

        return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public @ResponseBody
    ResponseEntity.BodyBuilder update(@PathVariable Long id, @RequestBody User newValue) {
        boolean successful = userService.update(id, newValue);

        if (!successful) return ResponseEntity.status(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/updateToken/{id}")
    public @ResponseBody
    ResponseEntity.BodyBuilder updateToken(@PathVariable Long id, @RequestBody String token) {
        boolean successful = userService.updateToken(id, token);
        if (!successful) return ResponseEntity.status(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK);

    }

    @DeleteMapping("/deleteAll")
    public @ResponseBody
    ResponseEntity.BodyBuilder deleteAll(@RequestHeader("Token") String token) {

        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        userService.deleteAll();

        return ResponseEntity.status(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public @ResponseBody
    ResponseEntity.BodyBuilder deleteById(@PathVariable Long userId, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK);

    }

}
