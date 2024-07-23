package com.msa2024.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

  private String email; //이메일
  private String phone_number; //전화번호
  private String name; //이름
  private String password; //비밀번호 : 전화번호 뒷자리
  private Role role; //사용자 역할
  private LocalDate blockDate; //시간이 지남에 따라 로그인 차단.
  private List<AttendanceRecord> attendanceRecords; //출퇴근 시간 기록.
  private int warningCount; //누적 경고 수
  private boolean blocked; //차단 여부

  public User() {
    // 기본 생성자
  }

  @JsonCreator
  public User(
          @JsonProperty("email") String email,
          @JsonProperty("name") String name,
          @JsonProperty("phone_number") String phone_number,
          @JsonProperty("password") String password,
          @JsonProperty("role") Role role,
          @JsonProperty("blockDate") LocalDate blockDate,
          @JsonProperty("attendanceRecords") List<AttendanceRecord> attendanceRecords,
          @JsonProperty("warningCount") int warningCount,
          @JsonProperty("blocked") boolean blocked
  ) {
    this.email = email;
    this.phone_number = phone_number;
    this.name = name;
    this.password = password;
    this.role = role;
    this.blockDate = blockDate;
    this.attendanceRecords = attendanceRecords != null ? attendanceRecords : new ArrayList<>();
    this.warningCount = warningCount;
    this.blocked = blocked;
  }

  public User(String email, String name, String phone_number, String password, Role role, LocalDate blockDate) {
    this.email = email;
    this.name = name;
    this.phone_number = phone_number;
    this.password = password;
    this.role = role;
    this.blockDate = blockDate;
    this.attendanceRecords = new ArrayList<>();
    this.warningCount = 0;  // 누적경고 수 초기화
    this.blocked = false;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDate getBlockDate() {
    return blockDate;
  }

  public void setBlockDate(LocalDate blockDate) {
    this.blockDate = blockDate;
  }

  public List<AttendanceRecord> getAttendanceRecords() {
    return attendanceRecords;
  }

  public void setAttendanceRecords(List<AttendanceRecord> attendanceRecords) {
    this.attendanceRecords = attendanceRecords;
  }

  public int getWarningCount() {
    return warningCount;
  }

  public void setWarningCount(int warningCount) {
    this.warningCount = warningCount;
  }

  public boolean isBlocked() {
    boolean result = blocked;
    System.out.println("isBlocked() called for user " + email + ": " + result + " (blocked=" + blocked + ", blockDate=" + blockDate + ")");
    return result;
  }

  public void addAttendanceRecord(AttendanceRecord record) {
    attendanceRecords.add(record);
  }

  public void addWarning() {
    this.warningCount++;
  }

  @Override
  public String toString() {
    return "User [email=" + email + ", phone_number=" + phone_number + ", name=" + name
            + ", password=" + password + ", role=" + role + ", blockDate=" + blockDate
            + ", attendanceRecords=" + attendanceRecords + ", warningCount=" + warningCount
            + ", blocked=" + blocked + "]";
  }
}
