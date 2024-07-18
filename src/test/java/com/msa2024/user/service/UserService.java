package com.msa2024.user.service;

import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;

import java.util.List;

public interface UserService {
  
  //void warningUser(); //유저 경고
  void showAdminFunction(); //관리자 뷰
  void showUserFunction(); //유저 뷰
  void loadUsersSignUpFile(String filePath);//회원명단
  boolean register(String email, String name, String password, Role role);//회원 등록.
  User login(String email, String password);//로그인
  void logout(String email);//로그아웃
  void reportUser(String email);//유저 경고
  List<User> getUsers();
  void saveUsers();  // 이 메서드 추가
  User getUserByEmail(String email);  // 이 메서드 추가

}
