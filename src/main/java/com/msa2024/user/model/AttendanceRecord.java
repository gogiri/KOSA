package com.msa2024.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceRecord {
  
  private LocalDateTime loginTime; //로그인 시간.
  private LocalDateTime logoutTime; //로그아웃 시간.
  private boolean isLate; //지각유무.
  private boolean isEarlyLeave; //기타사유.
  
  
  public AttendanceRecord(LocalDateTime loginTime) {
    this.loginTime = loginTime;
    //9시 이후 지각
    this.isLate = loginTime.toLocalTime().isAfter(LocalTime.of(9, 0));
}
  public void setLoginTime(LocalDateTime loginTime) {
    this.loginTime = loginTime;
  }
  public LocalDateTime getLogoutTime() {
    return logoutTime;
  }
  public void setLogoutTime(LocalDateTime logoutTime) {
    this.logoutTime = logoutTime;
    //19시 이전에는 기타사유
    this.isEarlyLeave = logoutTime.toLocalTime().isBefore(LocalTime.of(19, 0));
  }
  public boolean isLate() {
    return isLate;
  }
  public void setLate(boolean isLate) {
    this.isLate = isLate;
  }
  public boolean isEarlyLeave() {
    return isEarlyLeave;
  }
  public void setEarlyLeave(boolean isEarlyLeave) {
    this.isEarlyLeave = isEarlyLeave;
  }
  
  
  
  

}
