package com.msa2024.admin.service;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.user.model.User;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminManager adminManager;

    public AdminServiceImpl(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    @Override
    public void listAllUsers() {
        List<User> users = adminManager.listAllUsers();
        for (User user : users) {
            System.out.println("이름: " + user.getName() + ", 이메일: " + user.getEmail() + ", 전화번호: " + user.getPhone_number());
        }
    }

    @Override
    public void blockUser(String email, int hours) {
        adminManager.blockUser(email, hours);
    }

    @Override
    public void checkForBlockedUsers() {
        adminManager.checkForBlockedUsers();
    }

    @Override
    public void updateUser(String email, String newName, String newPassword) {
        adminManager.updateUser(email, newName, newPassword);
    }

    @Override
    public void listBlacklistedUsers() {
        adminManager.listBlacklistedUsers();
    }

    @Override
    public void addAnnouncement(String announcement) {
        adminManager.addAnnouncement(announcement);
    }

    @Override
    public void listAnnouncements() {
        adminManager.listAnnouncements();
    }

    @Override
    public void listActivityLogs() {
        adminManager.listActivityLogs();
    }

    @Override
    public void unblockUser(String email) {
        adminManager.unblockUser(email);
    }

    @Override
    public void checkNoShows() {
        adminManager.checkNoShows();
    }
}
