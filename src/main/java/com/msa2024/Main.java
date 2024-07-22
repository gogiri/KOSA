package com.msa2024;

import java.util.List;
import java.util.Scanner;

import com.msa2024.admin.controller.AdminController;
import com.msa2024.admin.entity.AdminManager;
import com.msa2024.club.controller.ClubController;
import com.msa2024.reservation.controller.ReservationController;
import com.msa2024.user.controller.UserController;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.service.UserService;
import com.msa2024.user.service.UserServiceImpl;

public class Main {
    private static UserController userController = new UserController();
    private static ReservationController reservationController = new ReservationController();
    private static UserService userService = new UserServiceImpl("src/main/java/resources/");
    private static ClubController clubController;
    private static AdminController adminController;
    private static AdminManager adminManager = new AdminManager(UserController.getUserManager());
    private static User loggedInUser = null; // 로그인된 사용자 정보를 저장할 변수
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

        // 공지사항 출력
        showAnnouncements();

        runProgram();
    }

    private static void showAnnouncements() {
        List<String> announcements = adminManager.getAnnouncements();
        if (announcements.isEmpty()) {
            System.out.println("==== [공지사항] ====");
            System.out.println("현재 공지사항이 없습니다.");
        } else {
            System.out.println("==== [공지사항] ====");
            for (String announcement : announcements) {
                System.out.println("- " + announcement);
            }
        }
        System.out.print("=======================================================\n");
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
                    case RESERVATION_MENU:
                        showReservationMenu(sc);
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
        System.out.println("[4] 블랙리스트 확인");
        System.out.println("[5] 종료");
        System.out.print("메뉴를 선택하세요 => ");

        if (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    state = ProgramState.LOGIN_REGISTER;
                    break;
                case "2":
                    if (loggedInUser != null) {
                        Role role = loggedInUser.getRole();
                        if (role == Role.USER || role == Role.STUDENT) {
                            state = ProgramState.USER_MENU;
                        } else {
                            System.out.println("\n올바르지 않은 사용자 역할입니다.");
                        }
                    } else {
                        System.out.println("\n로그인 후 이용 가능합니다.");
                    }
                    break;
                case "3":
                    if (loggedInUser != null && loggedInUser.getRole() == Role.ADMIN) {
                        state = ProgramState.ADMIN_MENU;
                    } else {
                        System.out.println("\n관리자만 이용 가능합니다.");
                    }
                    break;
                case "4":
                    adminManager.listBlacklistedUsers();
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
                    loggedInUser = UserController.login(sc);
                    if (loggedInUser != null) {
                        Role role = loggedInUser.getRole();
                        System.out.println("로그인한 사용자 역할: " + role);
                        if (role == Role.USER || role == Role.STUDENT) {
                            state = ProgramState.USER_MENU;
                        } else if (role == Role.ADMIN) {
                            adminController = new AdminController(UserController.getUserManager(), userService);
                            state = ProgramState.ADMIN_MENU;
                        } else {
                            System.out.println("\n올바르지 않은 사용자 역할입니다.");
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
                    System.out.println(loggedInUser.getEmail());
                    state = ProgramState.RESERVATION_MENU;
                    break;
                case "2":
                    state = ProgramState.CLUB_MENU;
                    break;
                case "3":
                    loggedInUser = null;
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
        if (loggedInUser != null && loggedInUser.getRole() == Role.ADMIN) {
            if (adminController == null) {
                adminController = new AdminController(UserController.getUserManager(), userService);
            }
            adminController.run(); // AdminController의 run 메서드를 호출
            state = ProgramState.MAIN_MENU; // adminView가 종료되면 메인 메뉴로 돌아감
        } else {
            System.out.println("관리자 권한이 없습니다.");
            state = ProgramState.MAIN_MENU;
        }
    }

    private static void showClubMenu(Scanner sc) {
        ClubController clubController = new ClubController(loggedInUser); // 로그인된 유저 정보를 ClubController에 전달
        clubController.run();
        if (clubController.isExitRequested()) {
            state = ProgramState.USER_MENU;
        } else {
            state = ProgramState.EXIT;
        }
    }

    private static void showReservationMenu(Scanner sc) {
        reservationController.run();
        if (reservationController.isExitRequested()) {
            state = ProgramState.USER_MENU;
        } else {
            state = ProgramState.EXIT;
        }
    }
}
