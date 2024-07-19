package com.msa2024.admin.service;

import com.msa2024.user.model.User;

import java.util.List;

public interface AdminService {
    void listAllUsers();
    void blockUser(String email, int hours);
    void checkForBlockedUsers();
    void updateUser(String email, String newName, String newPassword);
    void listBlacklistedUsers();
    void addAnnouncement(String announcement);
    void listAnnouncements();
    void listActivityLogs();
    void unblockUser(String email);
    void checkNoShows();
}
