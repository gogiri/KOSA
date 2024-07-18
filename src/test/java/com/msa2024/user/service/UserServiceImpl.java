package com.msa2024.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.user.model.AttendanceRecord;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class UserServiceImpl implements UserService {

    private Map<String, User> users = new HashMap<>();
    private Map<String, AttendanceRecord> currentSessions = new HashMap<>();
    private String filePath;

    public UserServiceImpl(String filePath) {
        this.filePath = filePath;
        loadUsersSignUpFile(filePath);
    }

    @Override
    public void loadUsersSignUpFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> userList = objectMapper.readValue(new File(filePath), new TypeReference<List<User>>() {});
            for (User user : userList) {
                users.put(user.getEmail(), user);
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
    }

    @Override
    public boolean register(String email, String name, String password, Role role) {
        if (users.containsKey(email)) {
            System.out.println("Email already registered.");
            return false;
        }

        if (email == null || name == null || password == null || role == null) {
            System.out.println("All fields are required.");
            return false;
        }

        LocalDate blockDate = LocalDate.now().plusMonths(6);
        User user = new User(email, name, password, role, blockDate);
        users.put(email, user);
        saveUsers();  // 사용자 등록 후 저장
        System.out.println("회원가입이 완료되었습니다!!. 해당 계정은 6개월 후에 잠길 예정입니다.");
        return true;
    }

    @Override
    public User login(String email, String password) {
        if (!users.containsKey(email)) {
            System.out.println("User not found.");
            return null;
        }

        User user = users.get(email);
        if (user.isBlocked()) {
            System.out.println("User is blocked.");
            return null;
        }

        if (user.getPassword().equals(password)) {
            System.out.println("로그인 되었습니다!");
            AttendanceRecord record = new AttendanceRecord(LocalDateTime.now());
            user.addAttendanceRecord(record);
            currentSessions.put(email, record);
            return user;
        } else {
            System.out.println("Incorrect password.");
            return null;
        }
    }

    @Override
    public void logout(String email) {
        if (currentSessions.containsKey(email)) {
            AttendanceRecord record = currentSessions.remove(email);
            record.setLogoutTime(LocalDateTime.now());
            System.out.println("Logout successful.");

            if (record.isEarlyLeave()) {
                System.out.println("Early leave detected.");
            }
        } else {
            System.out.println("No active session found for the user.");
        }
    }

    @Override
    public void reportUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.addWarning();
            System.out.println("User " + email + " reported. Warning count: " + user.getWarningCount());
        } else {
            System.out.println("User not found.");
        }
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void saveUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), new ArrayList<>(users.values()));
        } catch (IOException e) {
            System.out.println("Error saving user file: " + e.getMessage());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }
}
