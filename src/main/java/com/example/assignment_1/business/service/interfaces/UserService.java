package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.User;
import com.example.assignment_1.data.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();
    User findById(Long userId);
    List<User> findAllByRole(UserRole role);
    User findByUsername(String username);
    User findByToken(String token);
    void save(User user);
    boolean update(Long id, User newValue);
    boolean updateToken(Long id, String token);
    void deleteById(Long userId);
}
