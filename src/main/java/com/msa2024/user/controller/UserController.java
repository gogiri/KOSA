package com.msa2024.user.controller;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.msa2024.admin.entity.AdminManager;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserServiceImpl;

public class
UserController {

    private static UserManager userManager;
    private static User loggedInUser;
    private static AdminManager adminManager; // AdminManager 인스턴스 추가

    public UserController() {
        UserServiceImpl service = new UserServiceImpl("src/main/java/resources/");
        userManager = new UserManager(service);
        adminManager = new AdminManager(userManager); // AdminManager 초기화
        System.out.println(userManager.toString());
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static User login(Scanner sc) {
        System.out.print("이메일: ");
        String loginEmail = sc.nextLine();
        System.out.print("비밀번호: ");
        String loginPassword = sc.nextLine();
        loggedInUser = userManager.loginUser(loginEmail, loginPassword);
        if (loggedInUser != null) {
            if (loggedInUser.isBlocked()) {
                System.out.println("이 사용자는 차단되었습니다. 로그인할 수 없습니다.");
                loggedInUser = null;
            } else {
                System.out.println("\n[INFO] " + loggedInUser.getName() + "님 환영합니다!");
            }
        } else {
            System.out.println("로그인 실패! 이메일이나 비밀번호를 확인하세요.");
        }
        return loggedInUser;
    }


    public void register(Scanner sc) {
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("이메일: ");
        String email = sc.nextLine();
        System.out.print("전화번호: ");
        String phoneNumber = sc.nextLine();
        String password = phoneNumber.substring(phoneNumber.length() - 4);
        System.out.println("\n[INFO] 유저인지 관리자인지 선택해주세요!\n"
                + "[1] 유저\t[2] 관리자\t[3] 나가기");
        System.out.print("메뉴 => ");
        String roleChoice = sc.nextLine();
        if (roleChoice.equals("1")) {
            userManager.registerUser(email, name, phoneNumber, password, Role.USER);
            System.out.println("[회원가입] : " + userManager.toString());
        } else if (roleChoice.equals("2")) {
            userManager.registerUser(email, name, phoneNumber, password, Role.ADMIN);
            System.out.println("[회원가입] : " + userManager.toString());
        }
    }

    public void logout() {
        if (loggedInUser != null) {
            userManager.logoutUser(loggedInUser.getEmail());
            loggedInUser = null;
            System.out.println("로그아웃 되었습니다.");
        } else {
            System.out.println("로그인된 사용자가 없습니다.");
        }
    }

    public void userView(Scanner sc) {
        boolean userLoop = true;
        while (userLoop) {
            System.out.println("\n[INFO] 메뉴를 선택해주세요!!\n"
                    + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
            System.out.print("메뉴 => ");
            String userMenu = sc.nextLine();
            switch (userMenu) {
                case "1":
                    //MeetingRoomController meetingRoomController = new MeetingRoomController();
                    //meetingRoomController.run();
                    break;
                case "2":
                    //ClubController clubController = new ClubController();
                    //clubController.run();

                    break;
                case "3":
                    logout();
                    userLoop = false;
                    break;
                default:
                    System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                    break;
            }
        }
    }
}
