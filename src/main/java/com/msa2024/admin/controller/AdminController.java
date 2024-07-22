package com.msa2024.admin.controller;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.admin.service.AdminService;
import com.msa2024.admin.service.AdminServiceImpl;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserService;

import java.util.Scanner;

public class AdminController {
    private boolean exitRequested = false; // 프로그램 종료 요청 여부를 나타내는 변수
    private AdminManager adminManager;
    private AdminService adminService;


    public AdminController(UserManager userManager, UserService userService) {
        this.adminManager = new AdminManager(userManager);
        this.adminService = new AdminServiceImpl(adminManager, userService);
    }

    /**
     * 관리자 메뉴를 실행합니다.
     * @param sc Scanner 인스턴스
     */
    public void run() {
        adminView(new Scanner(System.in));
    }

    /**
     * 관리자 메뉴를 보여주고 사용자 입력에 따라 적절한 작업을 수행합니다.
     * @param sc Scanner 인스턴스
     */
    public void adminView(Scanner sc) {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println("\n[INFO] 관리자님 환영합니다!\n"
                    + "메뉴를 선택해주세요!!\n"
                    + "[1] 모든 회원 출력\t[2] 사용자 차단\t[3] 사용자 차단 해제\t"
                    + "[4] 회원정보 수정\t[5] 공지사항 추가\t[6] 공지사항 목록 보기\t[7] 종료");
            System.out.print("메뉴 => ");
            String adminMenu = sc.nextLine();
            System.out.println("선택된 메뉴: " + adminMenu);  // 선택된 메뉴 출력

            switch (adminMenu) {
                case "1":
                    adminService.listAllUsers();  // 모든 회원 출력
                    break;
                case "2":
                    System.out.print("차단할 사용자 이메일: ");
                    String blockEmail = sc.nextLine();
                    System.out.print("차단할 시간(시간 단위): ");
                    int hours = sc.nextInt();
                    sc.nextLine();  // 개행 문자 제거
                    adminService.blockUser(blockEmail, hours);  // 사용자 차단
                    break;
                case "3":
                    System.out.print("차단 해제할 사용자 이메일: ");
                    String unblockEmail = sc.nextLine();
                    adminService.unblockUser(unblockEmail);  // 사용자 차단 해제
                    break;
                case "4":
                    System.out.print("수정할 사용자 이메일: ");
                    String updateEmail = sc.nextLine();
                    System.out.print("새 이름(공백으로 유지): ");
                    String newName = sc.nextLine();
                    System.out.print("새 비밀번호(공백으로 유지): ");
                    String newPassword = sc.nextLine();
                    adminService.updateUser(updateEmail, newName, newPassword);  // 회원정보 수정
                    break;
                case "5":
                    System.out.print("추가할 공지사항: ");
                    String announcement = sc.nextLine();
                    adminService.addAnnouncement(announcement);  // 공지사항 추가
                    break;
                case "6":
                    adminService.listAnnouncements();  // 공지사항 목록 보기
                    break;
                case "7":
                    logout();
                    adminLoop = false;  // 종료
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    /**
     * 프로그램 종료 요청 여부를 반환합니다.
     * @return 종료 요청 여부
     */
    public boolean isExitRequested() {
        return exitRequested;
    }

    /**
     * 로그아웃을 처리합니다.
     * 프로그램 종료 요청을 설정합니다.
     */
    private void logout() {
        exitRequested = true;
        System.out.println("로그아웃 되었습니다.");
    }
}
