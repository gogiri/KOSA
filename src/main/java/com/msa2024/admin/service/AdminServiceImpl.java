package com.msa2024.admin.service;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.user.model.User;
import com.msa2024.user.service.UserService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminManager adminManager;
    private UserService userService;

    public AdminServiceImpl(AdminManager adminManager, UserService userService) {
        this.adminManager = adminManager;
        this.userService = userService;
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
        User user = userService.getUsers().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setBlocked(true); // 차단 상태 업데이트
            user.addWarning();
            userService.saveBlockUsers(); // 사용자 변경사항을 저장하는 메서드 호출
            System.out.println("사용자 " + email + "이 차단되었습니다.");
            System.out.println("차단된 사용자 정보: " + user); // 디버깅 출력
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public void unblockUser(String email) {
        User user = userService.getUsers().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setBlocked(false); // 차단 해제
            userService.saveBlockUsers(); // 사용자 변경사항을 저장하는 메서드 호출
            System.out.println("사용자 " + email + "의 차단이 해제되었습니다.");
            System.out.println("차단 해제된 사용자 정보: " + user); // 디버깅 출력
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
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
}
