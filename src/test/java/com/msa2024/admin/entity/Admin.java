package com.msa2024.admin.entity;

import com.msa2024.user.model.User;

public class Admin extends User {
    public Admin(String name, String phoneNumber, String email, String password) {
        super(name, phoneNumber, email, password);
    }
}
