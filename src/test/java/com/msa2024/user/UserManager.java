package com.msa2024.user;

import java.util.List;
import java.util.HashMap;
import com.msa2024.util.GenericFileUtil;

public class UserManager {
    private List<User> users;
    private User currentUser;
    private static final String USERS_FILE = "students.json";
    private GenericFileUtil<User> fileUtil;

    public UserManager(List<User> users) {
        this.users = users;
        this.fileUtil = new GenericFileUtil<>();
        loadUsers();
    }

    public boolean login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("로그인 성공! 환영합니다, " + user.getName() + "님.");
                return true;
            }
        }
        System.out.println("로그인 실패. 이메일 또는 비밀번호를 확인해주세요.");
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void saveUsers() {
        fileUtil.writeToFile(USERS_FILE, users);
    }

    public void loadUsers() {
        List<User> loadedUsers = fileUtil.readFromFile(USERS_FILE);
        if (loadedUsers != null) {
            users.addAll(loadedUsers);
        }
    }

    public void printAllUsers() {
        for (User user : users) {
            System.out.println("이름: " + user.getName() + ", 이메일: " + user.getEmail() + ", 전화번호: " + user.getPhoneNumber());
        }
    }

    public HashMap<String, User> getAllUsers() {
        HashMap<String, User> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getEmail(), user);
        }
        return userMap;
    }
}
