package com.msa2024.admin.entity;

import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.util.GenericFileUtil;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private UserManager userManager;
    private List<String> announcements;
    private GenericFileUtil<String> fileUtil;
    private List<String> activityLogs = new ArrayList<>(); // 활동 로그 목록
    private static final String ANNOUNCEMENTS_FILE = "announcements.json";

    public AdminManager(UserManager userManager) {
        this.userManager = userManager;
        this.fileUtil = new GenericFileUtil<>();
        this.announcements = fileUtil.readFromFile(ANNOUNCEMENTS_FILE, new TypeToken<List<String>>() {});

        if (this.announcements == null) {
            this.announcements = new ArrayList<>();
        }
    }

    public List<User> listAllUsers() {
        return userManager.listUsers();
    }

    public void blockUser(String email, int hours) {
        User user = userManager.getEmail(email);
        if (user != null) {
            LocalDateTime blockedUntilDateTime = LocalDateTime.now().plusHours(hours);
            user.setBlockDate(blockedUntilDateTime.toLocalDate());
            user.addWarning();
            userManager.saveUsers();
            System.out.println("사용자 " + email + "이 " + blockedUntilDateTime + "까지 차단되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    public void unblockUser(String email) {
        User user = userManager.getEmail(email);
        if (user != null) {
            user.setBlockDate(null);
            userManager.saveUsers();
            System.out.println("사용자 " + email + "의 차단이 해제되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    public void checkForBlockedUsers() {
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                user.setBlockDate(null);
                userManager.saveUsers();
                System.out.println("사용자 " + user.getEmail() + "의 차단이 자동으로 해제되었습니다.");
            }
        }
    }

    public void checkNoShows() {
        for (User user : userManager.listUsers()) {
            if (user.getWarningCount() >= 3) {
                blockUser(user.getEmail(), 72);
            }
        }
    }

    public void updateUser(String email, String newName, String newPassword) {
        User user = userManager.getEmail(email);
        if (user != null) {
            if (newName != null && !newName.isEmpty()) user.setName(newName);
            if (newPassword != null && !newPassword.isEmpty()) user.setPassword(newPassword);
            userManager.saveUsers();
            System.out.println("사용자 정보가 업데이트되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    public void listBlacklistedUsers() {
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 차단된 시간: " + user.getBlockDate() + ", 누적 경고 수: " + user.getWarningCount());
            }
        }
    }

    public void addAnnouncement(String announcement) {
        announcements.add(announcement);
        fileUtil.writeToFile(ANNOUNCEMENTS_FILE, announcements);
        System.out.println("공지사항이 추가되었습니다.");
    }

    public void listAnnouncements() {
        System.out.println("공지사항 목록:");
        for (String announcement : announcements) {
            System.out.println("- " + announcement);
        }
    }

    public void logActivity(String activity) {
        activityLogs.add(activity);
    }

    public void listActivityLogs() {
        System.out.println("사용자 활동 로그:");
        for (String log : activityLogs) {
            System.out.println("- " + log);
        }
    }

    public List<String> getAnnouncements() {
        return announcements;
    }
}
