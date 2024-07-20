package com.msa2024.admin.controller;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.admin.service.AdminService;
import com.msa2024.admin.service.AdminServiceImpl;
import com.msa2024.user.model.UserManager;

import java.util.Scanner;

public class AdminController {
    private boolean exitRequested = false;
    private AdminManager adminManager;
    private AdminService adminService;

    public AdminController(UserManager userManager) {
        this.adminManager = new AdminManager(userManager);
        this.adminService = new AdminServiceImpl(adminManager);
    }

    public void run() {
        adminView(new Scanner(System.in));
    }

    public void adminView(Scanner sc) {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println("\n[INFO] 관리자님 환영합니다!\n"
                    + "메뉴를 선택해주세요!!\n"
                    + "[1] 모든 회원 출력\t[2] 사용자 차단\t[3] 차단된 사용자 확인\t[4] 회원정보 수정\t"
                    + "[5] 블랙리스트 출력\t[6] 공지사항 추가\t[7] 공지사항 목록 보기\t"
                    + "[8] 사용자 활동 로그 보기\t[9] 사용자 차단 해제\t[10] 노쇼 확인 및 차단\t[11] 로그아웃\t[12] 뒤로가기");
            System.out.print("메뉴 => ");
            String adminMenu = sc.nextLine();
            switch (adminMenu) {
                case "1":
                    adminService.listAllUsers();
                    break;
                case "2":
                    System.out.print("차단할 사용자 이메일: ");
                    String blockEmail = sc.nextLine();
                    System.out.print("차단할 시간(시간 단위): ");
                    int hours = sc.nextInt();
                    sc.nextLine();  // 개행 문자 제거
                    adminService.blockUser(blockEmail, hours);
                    break;
                case "3":
                    adminService.checkForBlockedUsers();
                    break;
                case "4":
                    System.out.print("수정할 사용자 이메일: ");
                    String updateEmail = sc.nextLine();
                    System.out.print("새 이름(공백으로 유지): ");
                    String newName = sc.nextLine();
                    System.out.print("새 비밀번호(공백으로 유지): ");
                    String newPassword = sc.nextLine();
                    adminService.updateUser(updateEmail, newName, newPassword);
                    break;
                case "5":
                    adminService.listBlacklistedUsers();
                    break;
                case "6":
                    System.out.print("추가할 공지사항: ");
                    String announcement = sc.nextLine();
                    adminService.addAnnouncement(announcement);
                    break;
                case "7":
                    adminService.listAnnouncements();
                    break;
                case "8":
                    adminService.listActivityLogs();
                    break;
                case "9":
                    System.out.print("차단 해제할 사용자 이메일: ");
                    String unblockEmail = sc.nextLine();
                    adminService.unblockUser(unblockEmail);
                    break;
                case "10":
                    adminService.checkNoShows();
                    break;
                case "11":
                    logout();
                    adminLoop = false;
                    break;
                case "12":
                    adminLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    public boolean isExitRequested() {
        return exitRequested;
    }

    private void logout() {
        exitRequested = true;
        System.out.println("로그아웃 되었습니다.");
    }
}
