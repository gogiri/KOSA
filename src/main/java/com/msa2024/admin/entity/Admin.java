package com.msa2024.admin.entity;

import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;

import java.time.LocalDate;

public class Admin extends User {

    public Admin(String email, String name, String phone_number, String password, Role role, LocalDate blockDate) {
        super(email, name, phone_number, password, role, blockDate);
    }
}
