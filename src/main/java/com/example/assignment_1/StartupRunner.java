package com.example.assignment_1;

import com.example.assignment_1.business.controller.UserController;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.helper.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private UserController userController;


    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<User> response = userController.findByUsername("admin");
        User user = response.getBody();
        if (user == null || user.getId() == null) {
            userController.save(new User("admin", "admin", UserRole.Administrator));
        }
    }
}
