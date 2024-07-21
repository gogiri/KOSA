package com.msa2024.user.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.msa2024.user.model.AttendanceRecord;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.util.InputValidator;

public class UserServiceImpl implements UserService {

  private Map<String, User> users = new HashMap<>();
  private Map<String, AttendanceRecord> currentSessions = new HashMap<>();

  public UserServiceImpl(String filePath) {
    loadUsersSignUpFile(filePath);
  }

  @Override
  public void loadUsersSignUpFile(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      List<Map<String, String>> userList = objectMapper.readValue(new File(filePath), new TypeReference<List<Map<String, String>>>() {});
      for (Map<String, String> userMap : userList) {
        String email = userMap.get("email");
        String name = userMap.get("name");
        String phoneNumber = userMap.get("phone_number");
        String password = userMap.get("password"); // 파일에서 비밀번호를 직접 가져옵니다.
        Role role = Role.valueOf(userMap.get("role"));
        LocalDate blockDate = LocalDate.parse(userMap.get("blockDate")); // 파일에서 blockDate를 직접 가져옵니다.
        User user = new User(email, name, phoneNumber, password, role, blockDate);
        users.put(email, user);
        System.out.println("[userMap] : " + userMap.toString()); // 디버깅 메시지
      }
    } catch (IOException e) {
      System.out.println("Error reading user file: " + e.getMessage());
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

    if(!InputValidator.isValidName(name)) {
      System.out.println("이름 형식이 잘못되었습니다!!");
      return false;
    }

    if(!InputValidator.isValidEmail(email)) {
      System.out.println("이메일 형식이 잘못되었습니다!!");
      return false;
    }

    if(!InputValidator.isValidPhoneNumber(phone_number)) {
      System.out.println("전화번호 형식이 잘못되었습니다!!");
      return false;
    }

    LocalDate blockDate = LocalDate.now().plusMonths(6);
    User user = new User(email, name, phone_number, password, role, blockDate);
    users.put(email, user);
    System.out.println("회원가입이 완료되었습니다!!. 해당 계정은 6개월 후에 잠길 예정입니다.");
    return true;
  }

  @Override
  public User login(String email, String password) {
    if (!users.containsKey(email)) {
      System.out.println("이메일 정보가 일치하지 않습니다.");
      return null;
    }

    User user = users.get(email);
    if (user.isBlocked()) {
      System.out.println(" 로그인 정지 상태입니다.");
      return null;
    }

    if(!InputValidator.isValidEmail(email)) {
      System.out.println("이메일 형식이 잘못되었습니다!!");
      return null;
    }

    if(!InputValidator.isValidPassword(password)) {
      System.out.println("비밀번호 형식이 잘못되었습니다.");
      return null;
    }

    if(user.getRole().equals(Role.PROFESSOR) || user.getRole().equals(Role.USER)) {
      System.out.println("관리자 및 학생만 로그인이 가능합니다!");
      return null;
    }

    if (user.getPassword().equals(password)) {
      System.out.println("로그인 되었습니다!");
      AttendanceRecord record = new AttendanceRecord(LocalDateTime.now());
      user.addAttendanceRecord(record);
      currentSessions.put(email, record);
      return user;
    } else {
      System.out.println("Incorrect password.");
      return null;
    }
  }

  @Override
  public void logout(String email) {
    if (currentSessions.containsKey(email)) {
      AttendanceRecord record = currentSessions.remove(email);
      record.setLogoutTime(LocalDateTime.now());
      System.out.println("Logout successful.");
      if (record.isEarlyLeave()) {
        System.out.println(record.getFormatterLogoutTime() + "기타사유로 인한 조퇴");
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
    // TODO Auto-generated method stub
  }

  @Override
  public void showUserFunction() {
    // TODO Auto-generated method stub
  }
}
