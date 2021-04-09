package com.example.assignment_1.data.repository;

import com.example.assignment_1.data.model.UserDB;
import com.example.assignment_1.helper.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDB,  Long> {
    public List<UserDB> findAllByRole(UserRole role);
    public UserDB findByUsername(String username);
    public UserDB findByToken(String token);
}
