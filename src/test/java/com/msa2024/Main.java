package com.msa2024;

import com.msa2024.admin.controller.AdminManager;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserServiceImpl;
import com.msa2024.util.DateUtil;
import com.msa2024.util.GenericFileUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // GenericFileUtil 인스턴스 초기화
        GenericFileUtil<String> fileUtil = new GenericFileUtil<>();

        String filename = "students.json";
        UserServiceImpl userService = new UserServiceImpl(filename);
        UserManager userManager = new UserManager(userService);
        AdminManager adminManager = new AdminManager(userManager);

        // 기본 관리자 계정 추가
        if (userManager.getUserByEmail("admin") == null) {
            userManager.registerUser("admin", "관리자", "admin", Role.ADMIN);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 로그인");
            System.out.println("2. 종료");
            int choice = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 제거

            if (choice == 1) {
                System.out.print("이메일: ");
                String email = scanner.nextLine();
                System.out.print("비밀번호: ");
                String password = scanner.nextLine();

                //bizbaeja 고친 부분

                User currentUser = userManager.loginedUser(email, password);
                if (currentUser != null) {
                    if (currentUser.getRole() == Role.ADMIN) {
                        // 관리자 기능 목록
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

                            if (adminChoice == 1) {
                                adminManager.listAllUsers();
                            } else if (adminChoice == 2) {
                                System.out.print("차단할 사용자 이메일: ");
                                String blockEmail = scanner.nextLine();
                                System.out.print("차단할 시간(시간 단위): ");
                                int hours = scanner.nextInt();
                                scanner.nextLine();  // 개행 문자 제거
                                adminManager.blockUser(blockEmail, hours);
                            } else if (adminChoice == 3) {
                                adminManager.checkForBlockedUsers();
                            } else if (adminChoice == 4) {
                                System.out.print("수정할 사용자 이메일: ");
                                String updateEmail = scanner.nextLine();
                                System.out.print("새 이름(공백으로 유지): ");
                                String newName = scanner.nextLine();
                                System.out.print("새 비밀번호(공백으로 유지): ");
                                String newPassword = scanner.nextLine();
                                adminManager.updateUser(updateEmail, newName, newPassword);
                            } else if (adminChoice == 5) {
                                adminManager.listBlacklistedUsers();
                            } else if (adminChoice == 6) {
                                System.out.print("추가할 공지사항: ");
                                String announcement = scanner.nextLine();
                                adminManager.addAnnouncement(announcement);
                            } else if (adminChoice == 7) {
                                adminManager.listAnnouncements();
                            }  else if (adminChoice == 9) {
                                adminManager.listActivityLogs();
                            } else if (adminChoice == 10) {
                                System.out.print("차단 해제할 사용자 이메일: ");
                                String unblockEmail = scanner.nextLine();
                                adminManager.unblockUser(unblockEmail);
                            } else if (adminChoice == 11) {
                                adminManager.checkNoShows();
                            } else if (adminChoice == 12) {
                                break;
                            } else {
                                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                            }
                        }
                    } else {
                        // 일반 사용자 기능 목록
                        System.out.println("로그인 성공! 환영합니다, " + currentUser.getName() + "님.");
                    }
                } else {
                    System.out.println("로그인 실패. 이메일 또는 비밀번호를 확인해주세요.");
                }
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }

        scanner.close();
        userManager.saveUsers(); // 프로그램 종료 시 사용자 데이터 저장
        fileUtil.writeToFile("announcements.json", adminManager.getAnnouncements()); // 프로그램 종료 시 공지사항 데이터 저장

    }
}
