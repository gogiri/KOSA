package com.msa2024.admin.controller;

import com.msa2024.user.User;
import com.msa2024.user.UserManager;
import com.msa2024.util.GenericFileUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminManager {
    private UserManager userManager;

    // 공지사항
    private List<String> announcements;
    private List<String> rooms;
    private GenericFileUtil<String> fileUtil;
    private List<String> activityLogs = new ArrayList<>(); // 활동 로그 목록
    private static final String ANNOUNCEMENTS_FILE = "announcements.json";
    private static final String ROOMS_FILE = "rooms.json";

    public AdminManager(UserManager userManager) {
        this.userManager = userManager;
        this.fileUtil = new GenericFileUtil<>();
        this.announcements = fileUtil.readFromFile(ANNOUNCEMENTS_FILE, new TypeToken<List<String>>() {});
        this.rooms = fileUtil.readFromFile(ROOMS_FILE, new TypeToken<List<String>>() {});
        if (this.announcements == null) {
            this.announcements = new ArrayList<>();
        }
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }
    }

    // 공지사항 추가
    public void addAnnouncement(String announcement) {
        announcements.add(announcement);
        fileUtil.writeToFile(ANNOUNCEMENTS_FILE, announcements);
        System.out.println("공지사항이 추가되었습니다.");
    }

    // 공지사항 목록 출력
    public void listAnnouncements() {
        System.out.println("공지사항 목록:");
        for (String announcement : announcements) {
            System.out.println("- " + announcement);
        }
    }

    // 방 추가
    public void addRoom(String roomName) {
        rooms.add(roomName);
        fileUtil.writeToFile(ROOMS_FILE, rooms);
        System.out.println("방이 추가되었습니다.");
    }

    // 모든 회원 출력
    public void listAllUsers() {
        userManager.printAllUsers();
    }

    // 사용자 차단
    public void blockUser(String email, int hours) {
        User user = userManager.getUser(email);
        if (user != null) {
            Date blockedUntil = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(hours));
            user.setBlockedUntil(blockedUntil);
            user.incrementBlockCount();
            userManager.saveUsers();
            System.out.println("사용자 " + email + "이 " + blockedUntil + "까지 차단되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    // 사용자 차단 해제
    public void unblockUser(String email) {
        User user = userManager.getUser(email);
        if (user != null) {
            user.setBlockedUntil(null);
            userManager.saveUsers();
            System.out.println("사용자 " + email + "의 차단이 해제되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    // 차단된 사용자 확인
    public void checkForBlockedUsers() {
        HashMap<String, User> users = userManager.getAllUsers();
        for (User user : users.values()) {
            if (user.getBlockedUntil() != null && new Date().after(user.getBlockedUntil())) {
                user.setBlockedUntil(null);
                userManager.saveUsers();
                System.out.println("사용자 " + user.getEmail() + "의 차단이 자동으로 해제되었습니다.");
            }
        }
    }

    // 노쇼 확인 및 차단
    public void checkNoShows() {
        HashMap<String, User> users = userManager.getAllUsers();
        for (User user : users.values()) {
            if (user.getNoShowCount() >= 3) {
                blockUser(user.getEmail(), 72);
            }
        }
    }

    // 회원 정보 수정
    public void updateUser(String email, String newName, String newPassword) {
        User user = userManager.getUser(email);
        if (user != null) {
            if (newName != null && !newName.isEmpty()) user.setName(newName);
            if (newPassword != null && !newPassword.isEmpty()) user.setPassword(newPassword);
            userManager.saveUsers();
            System.out.println("사용자 정보가 업데이트되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    // 블랙리스트 출력
    public void listBlacklistedUsers() {
        HashMap<String, User> users = userManager.getAllUsers();
        for (User user : users.values()) {
            if (user.getBlockedUntil() != null) {
                System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 차단된 시간: " + user.getBlockedUntil() + ", 차단 횟수: " + user.getBlockCount());
            }
        }
    }

    // 사용자 활동 로그 추가
    public void logActivity(String activity) {
        activityLogs.add(activity);
    }

    // 사용자 활동 로그 출력
    public void listActivityLogs() {
        System.out.println("사용자 활동 로그:");
        for (String log : activityLogs) {
            System.out.println("- " + log);
        }
    }

    public List<String> getAnnouncements() {
        return announcements;
    }

    public List<String> getRooms() {
        return rooms;
    }
}
