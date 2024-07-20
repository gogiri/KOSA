
package com.msa2024.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

  private User(UserBuilder builder) {
    this.email = builder.email;
    this.phone_number = builder.phone_number;
    this.name = builder.name;
    this.password = builder.password;
    this.role = builder.role;
    this.blockDate = builder.blockDate;
    this.attendanceRecords = builder.attendanceRecords;
    this.warningCount = builder.warningCount;
  }
  public static class UserBuilder {
    private String email;
    private String phone_number;
    private String name;
    private String password;
    private Role role;
    private LocalDate blockDate;
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();
    private int warningCount = 0;

    public UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder phoneNumber(String phone_number) {
      this.phone_number = phone_number;
      return this;
    }

    public UserBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder role(Role role) {
      this.role = role;
      return this;
    }

    public UserBuilder blockDate(LocalDate blockDate) {
      this.blockDate = blockDate;
      return this;
    }

    public UserBuilder attendanceRecords(List<AttendanceRecord> attendanceRecords) {
      this.attendanceRecords = attendanceRecords;
      return this;
    }

    public UserBuilder warningCount(int warningCount) {
      this.warningCount = warningCount;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
  public User(String email, String name, String phone_number, String password, Role role, LocalDate blockDate) {
    this.email = email;
    this.name = name;
    this.phone_number = phone_number;
    this.password = password;
    this.role = role;
    this.blockDate = blockDate;
    this.attendanceRecords = new ArrayList<AttendanceRecord>();
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



  public String getPhone_number() {
    return phone_number;
  }



  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
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
    if(blockDate == null) {
      return false;
    }
    return LocalDate.now().isAfter(blockDate);
  }

  public void addAttendanceRecord(AttendanceRecord record) {
    // TODO Auto-generated method stub
    attendanceRecords.add(record);

  }

  public void addWarning() {
    // TODO Auto-generated method stub
    this.warningCount++;

  }



  @Override
  public String toString() {

    return "User [email=" + email + ", phone_number=" + phone_number + ", name=" + name
        + ", password=" + password + ", role=" + role + ", blockDate=" + blockDate
        + ", attendanceRecords=" + attendanceRecords.toString() + ", warningCount=" + warningCount + "]";
  }







}



