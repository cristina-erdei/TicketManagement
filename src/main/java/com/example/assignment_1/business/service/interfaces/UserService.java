package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Administrator;
import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Cashier;
import com.example.assignment_1.business.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();
    User findById(Long userId);
    void save(User user);
    void deleteAll();
    void deleteById(Long userId);

    List<Cashier> getAllCashiers();
    List<Administrator> getAllAdministrators();
}
