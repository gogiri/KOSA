package com.msa2024.user.controller;

import java.util.Scanner;
import com.msa2024.club.service.ClubServiceImpl;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserServiceImpl;

public class UserController2 {
  
 
  private static UserManager userManager;
  private static User loggedInUser;

  public UserController2() {
      UserServiceImpl service = new UserServiceImpl("src/main/resources/students.json");
      userManager = new UserManager(service);
      System.out.println(userManager.toString());
  }

  public static User getLoggedInUser() {
      return loggedInUser;
  }

  public static void login(Scanner sc) {
      System.out.print("이메일: ");
      String loginEmail = sc.nextLine();
      System.out.print("비밀번호: ");
      String loginPassword = sc.nextLine();
      loggedInUser = userManager.loginUser(loginEmail, loginPassword);
      if (loggedInUser != null) {
          System.out.println("[유저정보] : " + loggedInUser.toString());
          System.out.println("\n[INFO] " + loggedInUser.getName() + "님 환영합니다!");
      } else {
          System.out.println("로그인 실패! 이메일이나 비밀번호를 확인하세요.");
      }
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

  public void adminView(Scanner sc) {
      boolean adminLoop = true;
      while (adminLoop) {
          System.out.println("\n[INFO] 관리자님 환영합니다!\n"
              + "메뉴를 선택해주세요!!\n"
              + "[1] 모든 회원 출력\t[2] 사용자 차단\t[3] 차단된 사용자 확인\t[4] 회원정보 수정\t"
              + "[5] 블랙리스트 출력\t[6] 공지사항 추가\t[7] 공지사항 목록 보기\t"
              + "[8] 사용자 활동 로그 보기\t[9] 사용자 차단 해제\t[10] 노쇼 확인 및 차단\t[11] 로그아웃");
          System.out.print("메뉴 => ");
          String adminMenu = sc.nextLine();
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
                  logout();
                  adminLoop = false;
                  break;
              default:
                  System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
                  break;
          }
      }
  }
  

}
