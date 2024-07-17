package com.msa2024.user.model;

import java.util.List;
import java.util.Scanner;
import com.msa2024.user.service.UserService;

public class UserManager {
  
    private UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    public void loadUsers(String filePath) {
      userService.loadUsersSignUpFile(filePath);
    }

    public void registerUser(String email, String name, String password, Role role) {
        userService.register(email, name, password, role);
    }

    public User loginUser(String email, String password) {
        return userService.login(email, password);
    }

    public void logoutUser(String email) {
        userService.logout(email);
    }

    public void reportUser(String email) {
        userService.reportUser(email);
    }

    public List<User> listUsers() {
        return userService.getUsers();
    }
}
  
  /*
  public void signUp() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n=================================================================\n");
    System.out.println("[INFO] 회원가입을 위해 아래의 양식을 채워주세요.\n");
    
    System.out.print("* 이메일 : ");
    String email = sc.next();  
    System.out.print("* 이름 : ");
    String name = sc.next();   
    System.out.print("* 비밀번호 : ");
    String password = sc.next();   
    
    
    System.out.println("회원가입이 완료되었습니다.");
    return;
  }
  
  
  public void signIn() {
  
    Scanner sc = new Scanner(System.in);
    System.out.print("* 이메일 : ");
    String email = sc.next();  
    System.out.print("* 비밀번호 : ");
    String password = sc.next();   
    
    
    System.out.println("로그인 되었습니다!!");
    
  }
  */
  
  
  
  


