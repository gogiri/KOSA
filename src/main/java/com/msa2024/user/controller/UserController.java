package com.msa2024.user.controller;

import java.util.Scanner;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserServiceImpl;

public class UserController {
  
  private UserManager userManager;

  public UserController() {
    UserServiceImpl service = new UserServiceImpl("C:/Users/SungJun/git/KOSA/src/main/resources/students.json");
      userManager = new UserManager(service);
  }
  
  
  public void run() {
    Scanner sc = new Scanner(System.in);
    System.out.print("=======================================================\n");
    System.out.print("=======================================================");
        
    
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
    System.out.print("=======================================================");
    
   
    boolean whileLoop = true;
    while (whileLoop) {
      try {
        System.out.println("\n[INFO] 안녕하세요!\n"
            + "교육생들을 위한 KOSA인트라넷입니다.\n"
            + "프로그램을 사용하시려면\n"
            + "메뉴를 선택해주세요!!\n"
            + "[1] 로그인\t[2] 회원가입\t[3] 종료");

        System.out.print("메뉴 => ");
        String menu = sc.nextLine();


        switch (menu) {
          case "1":
            //로그인 메서드.
            //manager.signIn();
          
            System.out.print("이메일: ");
            String loginEmail = sc.nextLine();
            System.out.print("비밀번호: ");
            String loginPassword = sc.nextLine();
            User user = userManager.loginUser(loginEmail, loginPassword);
            if (user != null && user.getRole() == Role.USER) {
              System.out.println(user.getAttendanceRecords().get(0)); //유저정보
                System.out.println("\n[INFO] " + user.getName() + "님 환영합니다!\n"
                    + "메뉴를 선택해주세요!!\n"
                    + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
                System.out.print("메뉴 => ");
                String userMenu = sc.nextLine();
                if ("3".equals(userMenu)) {
                    userManager.logoutUser(loginEmail);
                } else {
                    System.out.println("\n기능은 구현되지 않았습니다.");
                }
            }
            
            if (user != null && user.getRole() == Role.ADMIN) {
              System.out.println("\n[INFO] " + user.getName() + "관리자님 환영합니다!\n"
                  + "메뉴를 선택해주세요!!\n"
                  + "[1] 신고기능\t[2] 학생 전체조회\t[3] 로그아웃");
              System.out.print("메뉴 => ");
              String userMenu = sc.nextLine();
              if ("3".equals(userMenu)) {
                  userManager.logoutUser(loginEmail);
              } else {
                  System.out.println("\n기능은 구현되지 않았습니다.");
              }
          }
            break;
          case "2":
            System.out.print("이름: ");
            String name = sc.nextLine();
            System.out.print("이메일: ");
            String email = sc.nextLine();
            System.out.print("전화번호: ");
            String phoneNumber = sc.nextLine();
            String password = phoneNumber.substring(phoneNumber.length() - 4);
            System.out.println("\n[INFO] 유저인지 관리자인지 선택해주세요!\n"
                + "메뉴를 선택해주세요!!\n"
                + "[1] 유저\t[2] 관리자\t[3] 나가기");
            System.out.print("메뉴 => ");
            String users = sc.nextLine();
            if(users.equals("1")) {
              userManager.registerUser(email, name, password, Role.USER);
              System.out.println(userManager.toString());
              break;
            }
          
            else if(users.equals("2")) {
              userManager.registerUser(email, name, password, Role.ADMIN);
              System.out.println(userManager.toString());
              break;
            }
            
            
            
          case "3":
            System.out.println("\n[종료]");
            System.out.println("프로그램을 종료합니다.");
            whileLoop = false;
            break;
          default:
            System.out.println("\n없는 메뉴입니다. 다시 선택하세요");
            break;
        }

      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    sc.close();
    System.out.println("\n===== END =====");

  }

}
