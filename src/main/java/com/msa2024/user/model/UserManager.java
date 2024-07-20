package com.msa2024.user.model;

import java.util.List;
import com.msa2024.user.service.UserService;

public class UserManager {

    private UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
        List<User> users = listUsers();
        if (users == null || users.isEmpty()) {
            System.out.println("User list is null or empty.");
        } else {
            System.out.println("Loaded users: " + users.size());
        }
    }

    public void loadUsers(String filePath) {
        userService.loadUsersSignUpFile(filePath);
    }

    public void registerUser(String email, String name, String phone_number, String password, Role role) {
        userService.register(email, name, phone_number, password, role);
    }

    public User loginUser(String email, String password) {
        return userService.login(email, password);
    }

    public void logoutUser(String email) {
        userService.logout(email);
    }

    public void reportUser(String email) {
        userService.reportUser(email);
    }

    public List<User> listUsers() {
        return userService.getUsers();
    }
}
