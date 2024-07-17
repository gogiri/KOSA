
package com.msa2024.user.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
  
  private String email; //이메일
  private String telePhone; //전화번호
  private String name; //이름
  private String password; //비밀번호 : 전화번호 뒷자리
  private Role role; //사용자 역할
  private LocalDate blockDate; //시간이 지남에 따라 로그인 차단.
  private List<AttendanceRecord> attendanceRecords; //출퇴근 시간 기록.
  private int warningCount; //누적 경고 수
  

  public User(String email, String name, String password, Role role, LocalDate blockDate) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
    this.blockDate = blockDate;
    this.attendanceRecords = new ArrayList<>();
    this.warningCount = 0;  // 누적경고 수 초기화
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



  public String getTelePhone() {
    return telePhone;
  }



  public void setTelePhone(String telePhone) {
    this.telePhone = telePhone;
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
    // TODO Auto-generated method stub
    return false;
  }



  @Override
  public String toString() {
    return "User [email=" + email + ", telePhone=" + telePhone + ", name=" + name + ", password="
        + password + ", role=" + role + ", blockDate=" + blockDate + ", attendanceRecords="
        + attendanceRecords + ", warningCount=" + warningCount + "]";
  }



  public void addAttendanceRecord(AttendanceRecord record) {
    // TODO Auto-generated method stub
    
  }



  public void addWarning() {
    // TODO Auto-generated method stub
    
  }



 



  
  
  


}



