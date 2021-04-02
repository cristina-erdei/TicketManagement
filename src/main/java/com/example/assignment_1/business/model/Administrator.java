package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.UserRole;

public class Administrator extends User{
    public Administrator() {
        this.role = UserRole.Administrator;
    }
}
