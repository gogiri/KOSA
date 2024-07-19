package com.msa2024;

import java.util.Scanner;
import com.msa2024.club.controller.ClubController;
import com.msa2024.user.controller.UserController2;
import com.msa2024.user.model.Role;

public class Main2 {

    private static Scanner sc = new Scanner(System.in);
    private static UserController2 userController = new UserController2();
    private static ClubController clubController = new ClubController();

    public static void main(String[] args) {
        System.out.print("=======================================================\n");
        System.out.print("=======================================================\n");

        System.out.println("\n"
            + "####    ####   ########   ############    ######    \n"
            + "####   ####   ##########  ############   ########   \n"
            + "####  ####   ####    #### ####          ##########  \n"
            + "#########    ###      ### ############ ####    #### \n"
            + "#########    ###      ### ############ ############ \n"
            + "####  ####   ####    ####        ##### ############ \n"
            + "####   ####   ##########  ############ ####    #### \n"
            + "####    ####   ########   ############ ####    #### "
            + "                                                    "
            + "");
        System.out.print("=======================================================\n");
        System.out.print("=======================================================\n");

        startProgram();
    }

    private static void startProgram() {
        boolean mainLoop = true;
        while (mainLoop) {
            System.out.println("\n[메인 메뉴]");
            System.out.println("[1] 로그인/회원가입");
            System.out.println("[5] 종료");
            System.out.print("메뉴를 선택하세요 => ");

            String mainMenu = sc.nextLine();  // 입력이 있는지 확인

            switch (mainMenu) {
                case "1":
                    userLoginAndRegister();
                    break;
                case "5":
                    System.out.println("\n[종료]");
                    System.out.println("프로그램을 종료합니다.");
                    mainLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    // 로그인 및 회원가입
    private static void userLoginAndRegister() {
        boolean userLoop = true;
        while (userLoop) {
            System.out.println("\n[로그인/회원가입 메뉴]");
            System.out.println("[1] 로그인");
            System.out.println("[2] 회원가입");
            System.out.println("[3] 뒤로가기");
            System.out.print("메뉴를 선택하세요 => ");
            
            String userMenu = sc.nextLine();  // 입력이 있는지 확인

            switch (userMenu) {
                case "1":
                    UserController2.login(sc);
                    if (UserController2.getLoggedInUser() != null) {
                        if (UserController2.getLoggedInUser().getRole() == Role.USER) {
                            userMenu();
                        } else if (UserController2.getLoggedInUser().getRole() == Role.ADMIN) {
                            adminMenu();
                        }
                    }
                    break;
                case "2":
                    userController.register(sc);
                    break;
                case "3":
                    userLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    // 유저 접속 시, 보일 화면
    private static void userMenu() {
        boolean userLoop = true;
        while (userLoop) {
            System.out.println("\n[INFO] 메뉴를 선택해주세요!!\n"
                + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
            System.out.print("메뉴 => ");
            
            String userMenu = sc.nextLine();  // 문제 발생 지점

            switch (userMenu) {
                case "1":
                    System.out.println(UserController2.getLoggedInUser().getEmail());
                    // 회의실 예약 기능
                    // meetingRoomController.run();
                    break;
                case "2":
                    clubController.run();
                    break;
                case "3":
                    userController.logout();
                    userLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    // 회의실 메뉴작성
    private static void meetingRoomMenu() {
        // meetingRoomController.run();
    }

    // 관리자 메뉴작성
    private static void adminMenu() {
        boolean adminLoop = true;
        while (adminLoop) {
            System.out.println(
                "메뉴를 선택해주세요!!\n"
                + "[1] 모든 회원 출력\t[2] 사용자 차단\t[3] 차단된 사용자 확인\t[4] 회원정보 수정\t\n"
                + "[5] 블랙리스트 출력\t[6] 공지사항 추가\t[7] 공지사항 목록 보기\t"
                + "[8] 사용자 활동 로그 보기\t\n[9] 사용자 차단 해제\t\t[10] 노쇼 확인 및 차단\t[11] 로그아웃");
            System.out.print("메뉴 => ");
            
            String adminMenu = sc.nextLine();  // 입력이 있는지 확인

            switch (adminMenu) {
                case "1":
                    // 모든 회원 출력 로직
                    break;
                case "2":
                    // 사용자 차단 로직
                    break;
                case "3":
                    // 차단된 사용자 확인 로직
                    break;
                case "4":
                    // 회원정보 수정 로직
                    break;
                case "5":
                    // 블랙리스트 출력 로직
                    break;
                case "6":
                    // 공지사항 추가 로직
                    break;
                case "7":
                    // 공지사항 목록 보기 로직
                    break;
                case "8":
                    // 사용자 활동 로그 보기 로직
                    break;
                case "9":
                    // 사용자 차단 해제 로직
                    break;
                case "10":
                    // 노쇼 확인 및 차단 로직
                    break;
                case "11":
                    userController.logout();
                    adminLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }
}
