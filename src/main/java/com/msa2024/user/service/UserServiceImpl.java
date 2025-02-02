package com.msa2024.user.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.user.model.AttendanceRecord;
import com.msa2024.user.model.Role;
import com.msa2024.user.model.User;
import com.msa2024.util.InputValidator;
import com.fasterxml.jackson.core.type.TypeReference;

public class UserServiceImpl implements UserService {

  User user;
  private Map<String, User> users = new HashMap<>();
  private Map<String, AttendanceRecord> currentSessions = new HashMap<>();


  public UserServiceImpl(String filePath) {
    // TODO Auto-generated constructor stub
    loadUsersSignUpFile(filePath);
  }



  @Override
  public void loadUsersSignUpFile(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      List<Map<String, String>> userList = objectMapper.readValue(new File(filePath),
          new TypeReference<List<Map<String, String>>>() {});
      for (Map<String, String> userMap : userList) {

        String email = userMap.get("email");
        String name = userMap.get("name");
        String phoneNumber = userMap.get("phone_number");
        String password = phoneNumber.substring(phoneNumber.length() - 4); // 전화번호 뒷자리 4자리로 비밀번호 설정
        Role role = Role.USER;
        LocalDate blockDate = LocalDate.now().plusMonths(6);
        User user = new User(email, name, phoneNumber, password, role, blockDate);
        users.put(email, user);
         //System.out.println("[userList] : " + userList.toString());
         System.out.println("[userMap] : " + userMap.toString());
        /*
         * //키 벨류 값 출력. userMap.forEach((key, value) -> { System.out.println("Key: " + key +
         * ", Value: " + value); });
         */
        
      }
    } catch (IOException e) {
      System.out.println("Error reading user file: " + e.getMessage());
    }
  }

  @Override
  public boolean register(String email, String name, String phone_number, String password,
      Role role) {
    
    if (users.containsKey(email)) {
      System.out.println("해당 메일은 이미 등록된 메일입니다.");
      return false;
    }

    if (email == null || name == null || password == null || role == null) {
      System.out.println("모든 입력 필드를 작성해주세요.");
      return false;
    }
    
    //이름 정규식
    if(InputValidator.isValidName(name)) {
      System.out.println("이름 형식이 잘못되었습니다!!");
      return false;
    }
    
    
    //이메일 정규식
    if(InputValidator.isValidEmail(email)) {
      System.out.println("이메일 형식이 잘못되었습니다!!");
      return false;
    }
    
    //전화번호 정규식
    if(InputValidator.isValidPhoneNumber(phone_number)) {
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
    // 이메일 키값이 없을 때.
    if (!users.containsKey(email)) {
      System.out.println("이메일 정보가 일치하지 않습니다.");
      return null;
    }

    // 로그인이 막혔을 때.
    User user = users.get(email);
    if (user.isBlocked()) {
      System.out.println(" 로그인 정지 상태입니다.");
      return null;
    }
    
    //이메일 정규식
    if(!InputValidator.isValidEmail(email)) {
      System.out.println(InputValidator.isValidEmail(email) + "이메일 형식이 잘못되었습니다!!");
      return null;
    }
    
    //비밀번호 정규식 최소 8자 + 최소 한개의 영문자 + 최소 한개의 숫자
 

    // 로그인 시 패스워드가 일치 하고 로그인 될 때, 현재 시간도 저장.
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

    Scanner sc = new Scanner(System.in);
    System.out.println("\n[INFO]  [" + user.getName() + "]님 환영합니다!\n" + "메뉴를 선택해주세요!!\n"
        + "[1] 회의실 예약\t[2] 소모임 예약\t[3] 로그아웃");
    System.out.print("메뉴 => ");
    String view = sc.nextLine();

  }



}
