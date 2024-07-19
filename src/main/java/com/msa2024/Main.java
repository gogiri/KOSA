package com.msa2024;


import java.util.Scanner;
import com.msa2024.user.controller.UserController;




public class Main {
    public static void main(String[] args) {
      //private static Scanner sc = new Scanner(System.in);

 
      UserController controller = new UserController();
      controller.run(); //시작과 로그인, 회원가입 뷰


    }
}