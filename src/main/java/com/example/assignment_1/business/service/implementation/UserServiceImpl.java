package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.interfaces.UserService;
import com.example.assignment_1.data.model.UserDB;
import com.example.assignment_1.data.model.UserRole;
import com.example.assignment_1.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(User::new).collect(Collectors.toList());
    }

    @Override
    public User findById(Long userId) {
        Optional<UserDB> user = userRepository.findById(userId);
        return user.map(User::new).orElse(null);
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        return userRepository.findAllByRole(role).stream().map(User::new).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return new User(userRepository.findByUsername(username));
    }

    @Override
    public User findByToken(String token) {
        return new User(userRepository.findByToken(token));
    }

    @Override
    public void save(User user) {
        userRepository.save(new UserDB(user));
    }

    @Override
    public boolean update(Long id, User newValue) {
        Optional<UserDB> aux = userRepository.findById(id);

        if(aux.isEmpty()) return false;

        UserDB user = aux.get();
        user.setUsername(newValue.getUsername());
        user.setPassword(newValue.getPassword());
        user.setRole(newValue.getRole());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateToken(Long id, String token) {
        Optional<UserDB> aux = userRepository.findById(id);

        if(aux.isEmpty()) return false;

        UserDB user = aux.get();
        user.setToken(token);
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);

    }
}
