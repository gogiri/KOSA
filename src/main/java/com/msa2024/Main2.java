package com.msa2024;

import java.util.Scanner;
import com.msa2024.club.controller.ClubController;
import com.msa2024.user.controller.UserController2;
import com.msa2024.user.model.Role;

public class Main2 {

    private static UserController2 userController = new UserController2();
    private static ClubController clubController = new ClubController();
    private static ProgramState state = ProgramState.MAIN_MENU;  // 프로그램 상태 변수

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

        runProgram();
    }

    private static void runProgram() {
        try (Scanner sc = new Scanner(System.in)) {
            while (state != ProgramState.EXIT) {
                switch (state) {
                    case MAIN_MENU:
                        showMainMenu(sc);
                        break;
                    case LOGIN_REGISTER:
                        showLoginRegisterMenu(sc);
                        break;
                    case USER_MENU:
                        showUserMenu(sc);
                        break;
                    case ADMIN_MENU:
                        showAdminMenu(sc);
                        break;
                    case CLUB_MENU:
                        showClubMenu(sc);
                        break;
                    default:
                        state = ProgramState.EXIT;
                        break;
                }
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
    }

    private static void showMainMenu(Scanner sc) {
        System.out.println("\n[메인 메뉴]");
        System.out.println("[1] 로그인/회원가입");
        System.out.println("[2] 유저 메뉴");
        System.out.println("[3] 관리자 메뉴");
        System.out.println("[5] 종료");
        System.out.print("메뉴를 선택하세요 => ");

        if (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    state = ProgramState.LOGIN_REGISTER;
                    break;
                case "2":
                    if (UserController2.getLoggedInUser() != null && UserController2.getLoggedInUser().getRole() == Role.USER) {
                        state = ProgramState.USER_MENU;
                    } else {
                        System.out.println("\n로그인 후 이용 가능합니다.");
                    }
                    break;
                case "3":
                    if (UserController2.getLoggedInUser() != null && UserController2.getLoggedInUser().getRole() == Role.ADMIN) {
                        state = ProgramState.ADMIN_MENU;
                    } else {
                        System.out.println("\n관리자만 이용 가능합니다.");
                    }
                    break;
                case "5":
                    System.out.println("\n[종료]");
                    System.out.println("프로그램을 종료합니다.");
                    state = ProgramState.EXIT;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        } else {
            state = ProgramState.EXIT;
        }
    }

    private static void showLoginRegisterMenu(Scanner sc) {
        System.out.println("\n[로그인/회원가입 메뉴]");
        System.out.println("[1] 로그인");
        System.out.println("[2] 회원가입");
        System.out.println("[3] 뒤로가기");
        System.out.print("메뉴를 선택하세요 => ");

        if (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    UserController2.login(sc);
                    if (UserController2.getLoggedInUser() != null) {
                        if (UserController2.getLoggedInUser().getRole() == Role.USER) {
                            state = ProgramState.USER_MENU;
                        } else if (UserController2.getLoggedInUser().getRole() == Role.ADMIN) {
                            state = ProgramState.ADMIN_MENU;
                        }
                    }
                    break;
                case "2":
                    userController.register(sc);
                    break;
                case "3":
                    state = ProgramState.MAIN_MENU;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        } else {
            state = ProgramState.EXIT;
        }
    }

    private static void showUserMenu(Scanner sc) {
        System.out.println("\n[INFO] 메뉴를 선택해주세요!!\n"
                + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
        System.out.print("메뉴 => ");

        if (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(UserController2.getLoggedInUser().getEmail());
                    // 회의실 예약 기능
                    // meetingRoomController.run();
                    break;
                case "2":
                    state = ProgramState.CLUB_MENU;
                    break;
                case "3":
                    userController.logout();
                    state = ProgramState.MAIN_MENU;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        } else {
            state = ProgramState.EXIT;
        }
    }

    private static void showAdminMenu(Scanner sc) {
        System.out.println(
                "메뉴를 선택해주세요!!\n"
                        + "[1] 모든 회원 출력\t[2] 사용자 차단\t[3] 차단된 사용자 확인\t[4] 회원정보 수정\t\n"
                        + "[5] 블랙리스트 출력\t[6] 공지사항 추가\t[7] 공지사항 목록 보기\t"
                        + "[8] 사용자 활동 로그 보기\t\n[9] 사용자 차단 해제\t\t[10] 노쇼 확인 및 차단\t[11] 로그아웃");
        System.out.print("메뉴 => ");

        if (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
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
                    state = ProgramState.MAIN_MENU;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        } else {
            state = ProgramState.EXIT;
        }
    }

    private static void showClubMenu(Scanner sc) {
        clubController.run();
        if (clubController.isExitRequested()) {
            state = ProgramState.EXIT;
        } else {
            state = ProgramState.USER_MENU;
        }
    }
}
