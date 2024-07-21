package com.msa2024;

import java.util.Scanner;
import com.msa2024.admin.controller.AdminController;
import com.msa2024.user.controller.UserController2;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserService;
import com.msa2024.user.service.UserServiceImpl;

public class Main2 {

    private static UserService userService = new UserServiceImpl("src/main/resources/students.json");
    private static UserManager userManager = new UserManager(userService);
    private static UserController2 userController = new UserController2();
    private static AdminController adminController;
    private static ProgramState state = ProgramState.MAIN_MENU;  // 프로그램 상태 변수

    public static void main(String[] args) {
        userController.setUserManager(userManager);
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
                    User loggedInUser = UserController2.login(sc);
                    if (loggedInUser != null) {
                        if (loggedInUser.getRole() == Role.USER) {
                            state = ProgramState.USER_MENU;
                        } else if (loggedInUser.getRole() == Role.ADMIN) {
                            adminController = new AdminController(userManager, userService);
                            state = ProgramState.ADMIN_MENU;
                        }
                    } else {
                        System.out.println("로그인 실패! 이메일이나 비밀번호를 확인하세요.");
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
        User loggedInUser = UserController2.getLoggedInUser();
        if (loggedInUser != null && loggedInUser.getRole() == Role.USER) {
            userController.userView(sc);
        } else {
            System.out.println("\n로그인 후 이용 가능합니다.");
        }
        state = ProgramState.MAIN_MENU;
    }

    private static void showAdminMenu(Scanner sc) {
        if (adminController != null) {
            adminController.run();
            state = ProgramState.MAIN_MENU;
        } else {
            System.out.println("관리자 권한이 없습니다.");
            state = ProgramState.MAIN_MENU;
        }
    }
}
