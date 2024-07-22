package com.msa2024.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.msa2024.user.model.AttendanceRecord;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.util.GenericFileUtil;
import com.msa2024.util.InputValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class UserServiceImpl implements UserService {

  private Map<String, User> users = new HashMap<>();
  private Map<String, AttendanceRecord> currentSessions = new HashMap<>();
  private GenericFileUtil<User> fileUtil;
  private static final String USERS_FILE = "students.json";

  public UserServiceImpl(String basePath) {
    this.fileUtil = new GenericFileUtil<>(basePath);
    loadUsersSignUpFile(USERS_FILE);
  }

  @Override
  public void loadUsersSignUpFile(String filePath) {
    try {
      System.out.println("파일 경로: " + filePath);
      List<User> userList = fileUtil.readFromFileWithJackson(filePath, new TypeReference<List<User>>() {});
      System.out.println("사용자 목록: " + userList); // 디버깅 출력 추가
      if (userList != null) {
        for (User user : userList) {
          users.put(user.getEmail(), user);
          System.out.println("[User] : " + user.toString());
        }
      } else {
        System.out.println("User list is null.");
      }
    } catch (Exception e) {
      System.out.println("Error reading user file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public boolean register(String email, String name, String phone_number, String password, Role role) {
    if (users.containsKey(email)) {
      System.out.println("해당 메일은 이미 등록된 메일입니다.");
      return false;
    }

    if (email == null || name == null || password == null || role == null) {
      System.out.println("모든 입력 필드를 작성해주세요.");
      return false;
    }

    if (!InputValidator.isValidName(name)) {
      System.out.println("이름 형식이 잘못되었습니다!!");
      return false;
    }

    if (!InputValidator.isValidEmail(email)) {
      System.out.println("이메일 형식이 잘못되었습니다!!");
      return false;
    }

    if (!InputValidator.isValidPhoneNumber(phone_number)) {
      System.out.println("전화번호 형식이 잘못되었습니다!!");
      return false;
    }

    LocalDate blockDate = LocalDate.now().plusMonths(6);
    User user = new User(email, name, phone_number, password, role, blockDate);
    users.put(email, user);
    saveUsers();
    System.out.println("회원가입이 완료되었습니다!!. 해당 계정은 6개월 후에 잠길 예정입니다.");
    return true;
  }

  private void saveUsers() {
    List<User> userList = new ArrayList<>(users.values());
    fileUtil.writeToFileWithJackson(USERS_FILE, userList);
  }

  @Override
  public User login(String email, String password) {
    System.out.println("로그인 시도 - 이메일: " + email + ", 비밀번호: " + password);

    if (!users.containsKey(email)) {
      System.out.println("이메일 정보가 일치하지 않습니다.");
      return null;
    }

    User user = users.get(email);
    if (user.isBlocked()) {
      System.out.println(" 로그인 정지 상태입니다.");
      return null;
    }

    if (!InputValidator.isValidEmail(email)) {
      System.out.println("이메일 형식이 잘못되었습니다!!");
      return null;
    }

    if (user.getPassword().equals(password)) {
      System.out.println("로그인 되었습니다!");
      AttendanceRecord record = new AttendanceRecord(LocalDateTime.now());
      user.addAttendanceRecord(record);
      currentSessions.put(email, record);
      saveUsers(); // Save after login
      return user;
    } else {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return null;
    }
  }

  @Override
  public void logout(String email) {
    if (currentSessions.containsKey(email)) {
      AttendanceRecord record = currentSessions.remove(email);
      record.setLogoutTime(LocalDateTime.now());
      System.out.println("Logout successful.");
      saveUsers(); // Save after logout

      if (record.isEarlyLeave()) {
        System.out.println(record.getFormatterLogoutTime() + " 기타사유로 인한 조퇴");
      }
    } else {
      System.out.println("No active session found for the user.");
    }
  }

  @Override
  public void reportUser(String email) {
    User user = users.get(email);
    if (user != null) {
      user.addWarning();
      System.out.println("User " + email + " reported. Warning count: " + user.getWarningCount());
      saveUsers(); // Save after reporting
    } else {
      System.out.println("User not found.");
    }
  }

  @Override
  public List<User> getUsers() {
    return new ArrayList<>(users.values());
  }

  @Override
  public void showAdminFunction() {
    // 구현 생략
  }

  @Override
  public void showUserFunction() {
    // 구현 생략
  }
}
