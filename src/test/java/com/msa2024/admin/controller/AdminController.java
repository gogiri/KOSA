package com.msa2024.admin.controller;

import com.msa2024.admin.service.AdminManager;
import com.msa2024.user.model.UserManager;

import java.util.Scanner;

public class AdminController {
    private AdminManager adminManager;

    public AdminController(UserManager userManager) {
        this.adminManager = new AdminManager(userManager);
    }

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
            System.out.println("8. 방 추가");
            System.out.println("9. 사용자 활동 로그 보기");
            System.out.println("10. 사용자 차단 해제");
            System.out.println("11. 노쇼 확인 및 차단");
            System.out.println("12. 로그아웃");

            int adminChoice = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 제거

            switch (adminChoice) {
                case 1:
                    adminManager.listAllUsers();
                    break;
                case 2:
                    System.out.print("차단할 사용자 이메일: ");
                    String blockEmail = scanner.nextLine();
                    System.out.print("차단할 시간(시간 단위): ");
                    int hours = scanner.nextInt();
                    scanner.nextLine();  // 개행 문자 제거
                    adminManager.blockUser(blockEmail, hours);
                    break;
                case 3:
                    adminManager.checkForBlockedUsers();
                    break;
                case 4:
                    System.out.print("수정할 사용자 이메일: ");
                    String updateEmail = scanner.nextLine();
                    System.out.print("새 이름(공백으로 유지): ");
                    String newName = scanner.nextLine();
                    System.out.print("새 비밀번호(공백으로 유지): ");
                    String newPassword = scanner.nextLine();
                    adminManager.updateUser(updateEmail, newName, newPassword);
                    break;
                case 5:
                    adminManager.listBlacklistedUsers();
                    break;
                case 6:
                    System.out.print("추가할 공지사항: ");
                    String announcement = scanner.nextLine();
                    adminManager.addAnnouncement(announcement);
                    break;
                case 7:
                    adminManager.listAnnouncements();
                    break;

                case 9:
                    adminManager.listActivityLogs();
                    break;
                case 10:
                    System.out.print("차단 해제할 사용자 이메일: ");
                    String unblockEmail = scanner.nextLine();
                    adminManager.unblockUser(unblockEmail);
                    break;
                case 11:
                    adminManager.checkNoShows();
                    break;
                case 12:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
