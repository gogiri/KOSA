package com.msa2024.admin.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.util.GenericFileUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private UserManager userManager;
    private List<String> announcements;
    private GenericFileUtil<User> userFileUtil;
    private GenericFileUtil<String> announcementFileUtil;
    private List<String> activityLogs = new ArrayList<>(); // 활동 로그 목록
    private static final String ANNOUNCEMENTS_FILE = "announcements.json";

    public AdminManager(UserManager userManager) {
        this.userManager = userManager;
        this.userFileUtil = new GenericFileUtil<>("src/main/java/resources/");
        this.announcementFileUtil = new GenericFileUtil<>("src/main/java/resources/");

        try {
            this.announcementFileUtil.createFileIfNotExists(ANNOUNCEMENTS_FILE); // 파일이 없으면 생성
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> readAnnouncements = announcementFileUtil.readFromFileWithJackson(ANNOUNCEMENTS_FILE, new TypeReference<List<String>>() {});
        if (readAnnouncements == null) {
            this.announcements = new ArrayList<>();
        } else {
            this.announcements = new ArrayList<>(readAnnouncements);
        }
    }

    /**
     * 이메일을 통해 사용자 검색
     */
    private User getUserByEmail(String email) {
        for (User user : userManager.listUsers()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 사용자 정보를 파일에 저장
     */
    private void saveUsers() {
        userFileUtil.writeToFileWithJackson("students.json", userManager.listUsers());
    }

    /**
     * 모든 사용자 목록 반환
     */
    public List<User> listAllUsers() {
        return userManager.listUsers();
    }

    /**
     * 사용자를 차단
     */
    public void blockUser(String email, int hours) {
        User user = getUserByEmail(email);
        if (user != null) {
            LocalDateTime blockedUntilDateTime = LocalDateTime.now().plusHours(hours);
            user.setBlockDate(blockedUntilDateTime.toLocalDate());
            user.setBlocked(true); // 사용자 차단 상태 설정
            user.addWarning();
            saveUsers();
            System.out.println("사용자 " + email + "이 " + blockedUntilDateTime + "까지 차단되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 사용자 차단 해제
     */
    public void unblockUser(String email) {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setBlockDate(null);
            user.setBlocked(false); // 사용자 차단 해제
            saveUsers();
            System.out.println("사용자 " + email + "의 차단이 해제되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 차단된 사용자들을 확인하고 차단 해제
     */
    public void checkForBlockedUsers() {
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                user.setBlockDate(null);
                user.setBlocked(false); // 사용자 차단 해제
                saveUsers();
                System.out.println("사용자 " + user.getEmail() + "의 차단이 자동으로 해제되었습니다.");
            }
        }
    }

    /**
     * 노쇼 사용자 확인 및 차단(추후 기능 추가)
     *
     *    public void checkNoShows() {
     *         for (User user : userManager.listUsers()) {
     *             if (user.getWarningCount() >= 3) {
     *                 blockUser(user.getEmail(), 72);
     *             }
     *         }
     *     }
     */


    /**
     * 사용자 정보 업데이트
     */
    public void updateUser(String email, String newName, String newPassword) {
        User user = getUserByEmail(email);
        if (user != null) {
            if (newName != null && !newName.isEmpty()) user.setName(newName);
            if (newPassword != null && !newPassword.isEmpty()) user.setPassword(newPassword);
            saveUsers();
            System.out.println("사용자 정보가 업데이트되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 차단된 사용자 목록 출력
     */
    public void listBlacklistedUsers() {
        System.out.println("==== [블랙리스트] ====");
        boolean hasBlacklistedUsers = false;
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                hasBlacklistedUsers = true;
                System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 차단된 시간: " + user.getBlockDate() + ", 누적 경고 수: " + user.getWarningCount());
            }
        }
        if (!hasBlacklistedUsers) {
            System.out.println("현재 차단된 사용자가 없습니다.");
        }
        System.out.print("=======================================================\n");
    }

    /**
     * 공지사항 추가
     */
    public void addAnnouncement(String announcement) {
        announcements.add(announcement);
        announcementFileUtil.writeToFileWithJackson(ANNOUNCEMENTS_FILE, announcements);
        System.out.println("공지사항이 추가되었습니다.");
    }

    /**
     * 공지사항 목록 출력
     */
    public void listAnnouncements() {
        System.out.println("공지사항 목록:");
        for (String announcement : announcements) {
            System.out.println("- " + announcement);
        }
    }





    /**
     * 공지사항 반환
     */
    public List<String> getAnnouncements() {
        return announcements;
    }

    /**
     * 공지사항 설정
     */
    public void setAnnouncements(List<String> announcements) {
        this.announcements = announcements;
    }
}
