package com.msa2024;

import java.io.IOException;

import java.util.Scanner;
import com.msa2024.user.User;
import com.msa2024.user.UserManager;


public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
    
        // TODO Auto-generated method stub
        //System.out.println("===== START =====");

        //Manager manager = new Manager();
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();
        
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
                manager.signIn();
                System.out.println("\n[INFO] [@@@]님 환영합니다!\n"
                    + "메뉴를 선택해주세요!!\n"
                    + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
                System.out.print("메뉴 => ");
                String view = sc.nextLine();
               
                break;
              case "2":
                //회원가입 메서드.
                manager.signUp();
                System.out.println("\n[회원가입]");
                
                break;
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
=======
        System.out.println("Hello world!!");
>>>>>>> f8c729bfa924a1d60b65762a087050b44d30c62a
    }
}