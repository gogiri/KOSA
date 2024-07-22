package com.msa2024.admin.service;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.user.model.User;
import com.msa2024.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminManager adminManager;
    private UserService userService;

    /**
     * AdminServiceImpl 생성자
     * @param adminManager AdminManager 인스턴스
     * @param userService UserService 인스턴스
     */
    public AdminServiceImpl(AdminManager adminManager, UserService userService) {
        this.adminManager = adminManager;
        this.userService = userService;
    }

    /**
     * 모든 사용자 목록을 출력
     */
    @Override
    public void listAllUsers() {
        List<User> users = adminManager.listAllUsers();
        for (User user : users) {
            System.out.println("이름: " + user.getName() + ", 이메일: " + user.getEmail() + ", 전화번호: " + user.getPhone_number());
        }
    }

    /**
     * 사용자를 차단
     * @param email 차단할 사용자의 이메일
     * @param hours 차단 시간(시간 단위)
     */
    @Override
    public void blockUser(String email, int hours) {
        User user = userService.getUsers().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (user != null) {
            LocalDateTime blockedUntilDateTime = LocalDateTime.now().plusHours(hours);
            user.setBlockDate(blockedUntilDateTime.toLocalDate());
            user.addWarning();
            saveUsers(); // 사용자 변경사항을 저장하는 메서드 호출
            System.out.println("사용자 " + email + "이 " + blockedUntilDateTime + "까지 차단되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 사용자 변경사항을 저장하는 메서드
     */
    private void saveUsers() {
        // 사용자 변경사항을 저장하는 로직 구현
    }

    /**
     * 차단된 사용자들을 확인하고 차단 해제
     */
    @Override
    public void checkForBlockedUsers() {
        adminManager.checkForBlockedUsers();
    }

    /**
     * 사용자 정보 업데이트
     * @param email 수정할 사용자의 이메일
     * @param newName 새로운 이름
     * @param newPassword 새로운 비밀번호
     */
    @Override
    public void updateUser(String email, String newName, String newPassword) {
        adminManager.updateUser(email, newName, newPassword);
    }

    /**
     * 차단된 사용자 목록 출력
     */
    @Override
    public void listBlacklistedUsers() {
        adminManager.listBlacklistedUsers();
    }

    /**
     * 공지사항 추가
     * @param announcement 추가할 공지사항 내용
     */
    @Override
    public void addAnnouncement(String announcement) {
        adminManager.addAnnouncement(announcement);
    }

    /**
     * 공지사항 목록 출력
     */
    @Override
    public void listAnnouncements() {
        adminManager.listAnnouncements();
    }

    /**
     * 사용자 차단 해제
     * @param email 차단 해제할 사용자의 이메일
     */
    @Override
    public void unblockUser(String email) {
        adminManager.unblockUser(email);
    }

}
