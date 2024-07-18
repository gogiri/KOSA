package com.msa2024.user.model;

import com.msa2024.user.service.UserService;

import java.util.List;

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
//bizbaeja 수정

    public boolean loginUser(String email, String password) {
        return userService.login(email, password).isBlocked();
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

    // bizbaeja- 필요한 메서드
    public User getUserByEmail(String email) {
        return
    }

    public void saveUsers() {
    }

    public User loginedUser(String loginEmail, String loginPassword) {
        return  userService.login(loginEmail,loginPassword);
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
  
  
  
  


