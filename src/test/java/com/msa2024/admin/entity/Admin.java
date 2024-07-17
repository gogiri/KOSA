package com.msa2024.admin.entity;

import com.msa2024.user.User;

import java.util.Date;


public class Admin extends User {
    public Admin(String name, String phoneNumber, String email, String password) {
        super(name, phoneNumber, email, password);
    }

    // 관리자가 수행할 수 있는 추가 기능을 여기에 정의할 수 있습니다.
}
