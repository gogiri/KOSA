package com.msa2024.admin.service;

import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.google.gson.reflect.TypeToken;
import com.msa2024.util.GenericFileUtil;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminManager {
    private UserManager userManager;

    // 공지사항 및 방 목록
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

    // 관리자 메뉴 실행
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. 모든 회원 출력");
            System.out.println("2. 사용자 차단");
            System.out.println("3. 차단된 사용자 확인");
            System.out.println("4. 회원정보 수정");
            System.out.println("5. 블랙리스트 출력");
            System.out.println("6. 공지사항 추가");
            System.out.println("7. 공지사항 목록 보기");
            System.out.println("8. 사용자 활동 로그 보기");
            System.out.println("9. 사용자 차단 해제");
            System.out.println("10. 노쇼 확인 및 차단");
            System.out.println("11. 로그아웃");
            System.out.print("메뉴 선택: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 제거

            if (adminChoice == 1) {
                listAllUsers();
            } else if (adminChoice == 2) {
                System.out.print("차단할 사용자 이메일: ");
                String blockEmail = scanner.nextLine();
                System.out.print("차단할 시간(시간 단위): ");
                int hours = scanner.nextInt();
                scanner.nextLine();  // 개행 문자 제거
                blockUser(blockEmail, hours);
            } else if (adminChoice == 3) {
                checkForBlockedUsers();
            } else if (adminChoice == 4) {
                System.out.print("수정할 사용자 이메일: ");
                String updateEmail = scanner.nextLine();
                System.out.print("새 이름(공백으로 유지): ");
                String newName = scanner.nextLine();
                System.out.print("새 비밀번호(공백으로 유지): ");
                String newPassword = scanner.nextLine();
                updateUser(updateEmail, newName, newPassword);
            } else if (adminChoice == 5) {
                listBlacklistedUsers();
            } else if (adminChoice == 6) {
                System.out.print("추가할 공지사항: ");
                String announcement = scanner.nextLine();
                addAnnouncement(announcement);
            } else if (adminChoice == 7) {
                listAnnouncements();
            } else if (adminChoice == 8) {
                listActivityLogs();
            } else if (adminChoice == 9) {
                System.out.print("차단 해제할 사용자 이메일: ");
                String unblockEmail = scanner.nextLine();
                unblockUser(unblockEmail);
            } else if (adminChoice == 10) {
                checkNoShows();
            } else if (adminChoice == 11) {
                break;
            } else {
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
        scanner.close();
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

    // 모든 회원 출력
    public void listAllUsers() {
        for (User user : userManager.listUsers()) {
            System.out.println("이름: " + user.getName() + ", 이메일: " + user.getEmail() + ", 전화번호: " + user.getTelePhone());
        }
    }

    // 사용자 차단
    public void blockUser(String email, int hours) {
        User user = userManager.getUserByEmail(email);
        if (user != null) {
            LocalDateTime blockedUntilDateTime = LocalDateTime.now().plusHours(hours);
            LocalDate blockedUntil = blockedUntilDateTime.toLocalDate();
            user.setBlockDate(blockedUntil);
            user.addWarning();
            userManager.saveUsers();
            System.out.println("사용자 " + email + "이 " + blockedUntil + "까지 차단되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    // 사용자 차단 해제
    public void unblockUser(String email) {
        User user = userManager.getUserByEmail(email);
        if (user != null) {
            user.setBlockDate(null);
            userManager.saveUsers();
            System.out.println("사용자 " + email + "의 차단이 해제되었습니다.");
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    // 차단된 사용자 확인
    public void checkForBlockedUsers() {
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                user.setBlockDate(null);
                userManager.saveUsers();
                System.out.println("사용자 " + user.getEmail() + "의 차단이 자동으로 해제되었습니다.");
            }
        }
    }

    // 노쇼 확인 및 차단
    public void checkNoShows() {
        for (User user : userManager.listUsers()) {
            if (user.getWarningCount() >= 3) {
                blockUser(user.getEmail(), 72);
            }
        }
    }

    // 회원 정보 수정
    public void updateUser(String email, String newName, String newPassword) {
        User user = userManager.getUserByEmail(email);
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
        for (User user : userManager.listUsers()) {
            if (user.isBlocked()) {
                System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 차단된 시간: " + user.getBlockDate() + ", 누적 경고 수: " + user.getWarningCount());
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
}
