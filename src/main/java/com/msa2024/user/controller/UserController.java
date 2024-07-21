//package com.msa2024.user.controller;
//
//import java.util.Scanner;
//import com.msa2024.club.service.ClubServiceImpl;
//import com.msa2024.user.model.Role;
//import com.msa2024.user.model.User;
//import com.msa2024.user.model.UserManager;
//import com.msa2024.user.service.UserServiceImpl;
//
//public class UserController {
//  
//  private UserManager userManager;
//  
//
//
//  public UserController() {
//    UserServiceImpl service = new UserServiceImpl("src/main/resources/students.json");
//      userManager = new UserManager(service);
//      System.out.println(userManager.toString());
//      
//  }
//    
//  public void run() {
//    Scanner sc = new Scanner(System.in);
//    System.out.print("=======================================================\n");
//    System.out.print("=======================================================");
//        
//    
//    System.out.println("\n"
//        + "####    ####   ########   ############    ######    \n"
//        + "####   ####   ##########  ############   ########   \n"
//        + "####  ####   ####    #### ####          ##########  \n"
//        + "#########    ###      ### ############ ####    #### \n"
//        + "#########    ###      ### ############ ############ \n"
//        + "####  ####   ####    ####        ##### ############ \n"
//        + "####   ####   ##########  ############ ####    #### \n"
//        + "####    ####   ########   ############ ####    #### "
//        + "                                                    "
//        + "");
//    System.out.print("=======================================================\n");
//    System.out.print("=======================================================");
//    
//   
//    boolean whileLoop = true;
//    while (whileLoop) {
//      try {
//        System.out.println("\n[INFO] 안녕하세요!\n"
//            + "교육생들을 위한 KOSA인트라넷입니다.\n"
//            + "프로그램을 사용하시려면\n"
//            + "메뉴를 선택해주세요!!\n"
//            + "[1] 로그인\t[2] 회원가입\t[3] 종료");
//
//        System.out.print("메뉴 => ");
//        String menu = sc.nextLine();
//
//
//        switch (menu) {
//          case "1":
//            //로그인 메서드.
//            //manager.signIn();
//          
//            System.out.print("이메일: ");
//            String loginEmail = sc.nextLine();
//            System.out.print("비밀번호: ");
//            String loginPassword = sc.nextLine();
//            User user = userManager.loginUser(loginEmail, loginPassword);
//            if (user != null && user.getRole() == Role.USER) {
//              System.out.println("[유저정보] : " + user.toString()); //유저정보
//                System.out.println("\n[INFO] " + user.getName() + "님 환영합니다!\n"
//                    + "메뉴를 선택해주세요!!\n"
//                    + "[1] 회의실 메뉴\t[2] 소모임 메뉴\t[3] 로그아웃");
//                System.out.print("메뉴 => ");
//                String userMenu = sc.nextLine();
//                if("2".equals(userMenu)) {
//                  ClubServiceImpl clubServiceImpl = new ClubServiceImpl();
//                  sc = new Scanner(System.in);
//
//                  boolean whileLoop1 = true;
//                  while (whileLoop1) {
//                      try {
//                          System.out.println("[메뉴 선택]");
//                          System.out.println("1. 전체 출력");
//                          System.out.println("2. 등록");
//                          System.out.println("3. 수정");
//                          System.out.println("4. 삭제");
//                          System.out.println("5. 참가");
//                          System.out.println("9. 종료");
//                          System.out.print("메뉴를 선택하세요=>");
//                          String club_Menu = sc.nextLine();
//                         
//                          switch (club_Menu) {
//                          case "1":
//                              System.out.println("\n[전체 출력]");
//                              System.out.println(user.toString());
//                              clubServiceImpl.printClub();
//                              //cm.printClubs();
//                              break;
//                              
//                          case "2":
//                              System.out.println("\n[등록]");
//                              System.out.println(user.toString());
//                              clubServiceImpl.makingClub();
//                              //cm.ClubMaking();
//                              break;
//                              
//                          case "3":
//                              System.out.println("\n[수정]");
//                              //cm.ClubRetouch();
//                              //clubServiceImpl.
//                              break;
//                              
////                        case "4":
////                            System.out.println("\n[삭제]");
////                            cm.deletePerson();
////                            break;
//                              
////                        case "5":
////                            System.out.println("\n[참가]");
////                            cm.deletePerson();
////                            break;
//
//                          case "9":
//                              System.out.println("\n[종료]");
//                              System.out.println("프로그램을 종료합니다.");
//                              break;
//                          default:
//                              System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
//                              break;
//
//                          }
//                      } catch (Exception e) {
//
//                      }
//                  }
//                  sc.close();
//                  System.out.println("\n===== END =====");
//                }
//
//                if ("3".equals(userMenu)) {
//                    userManager.logoutUser(loginEmail);
//                } else {
//                    System.out.println("\n없는 메뉴입니다 다시 선택하세요.");
//                }
//            }
//            
//            if (user != null && user.getRole() == Role.ADMIN) {
//              System.out.println("\n[INFO] " + user.getName() + "관리자님 환영합니다!\n"
//                  + "메뉴를 선택해주세요!!\n");
//              System.out.println("[1] 모든 회원 출력");
//              System.out.println("[2] 사용자 차단");
//              System.out.println("[3] 차단된 사용자 확인");
//              System.out.println("[4] 회원정보 수정");
//              System.out.println("[5] 블랙리스트 출력");
//              System.out.println("[6] 공지사항 추가");
//              System.out.println("[7] 공지사항 목록 보기");
//              System.out.println("[8] 사용자 활동 로그 보기");
//              System.out.println("[9] 사용자 차단 해제");
//              System.out.println("[10] 노쇼 확인 및 차단");
//              System.out.println("[11] 로그아웃");
//              System.out.print("메뉴 => ");
//              String userMenu = sc.nextLine();
//              if ("3".equals(userMenu)) {
//                  userManager.logoutUser(loginEmail);
//              } else {
//                  System.out.println("\n기능은 구현되지 않았습니다.");
//              }
//          }
//            break;
//          case "2":
//            System.out.print("이름: ");
//            String name = sc.nextLine();
//            System.out.print("이메일: ");
//            String email = sc.nextLine();
//            System.out.print("전화번호: ");
//            String phoneNumber = sc.nextLine();
//            String password = phoneNumber.substring(phoneNumber.length() - 4);
//            System.out.println("\n[INFO] 유저인지 관리자인지 선택해주세요!\n"
//                + "메뉴를 선택해주세요!!\n"
//                + "[1] 유저\t[2] 관리자\t[3] 나가기");
//            System.out.print("메뉴 => ");
//            String users = sc.nextLine();
//            if(users.equals("1")) {
//              userManager.registerUser(email, name, phoneNumber, password, Role.USER);
//              System.out.println("[회원가입] : "+userManager.toString());
//              break;
//            }
//          
//            else if(users.equals("2")) {
//              userManager.registerUser(email, name, phoneNumber, password, Role.ADMIN);
//              System.out.println("[회원가입] : "+userManager.toString());
//              break;
//            }
//            
//            
//            
//          case "3":
//            System.out.println("\n[종료]");
//            System.out.println("프로그램을 종료합니다.");
//            whileLoop = false;
//            break;
//          default:
//            System.out.println("\n없는 메뉴입니다. 다시 선택하세요");
//            break;
//        }
//
//      } catch (Exception e) {
//        // TODO: handle exception
//      }
//    }
//    sc.close();
//    System.out.println("\n===== END =====");
//
//  }
//
//}
