package com.msa2024.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceRecord {
  /**
   * 출퇴근 시간 기록을 위한 필드
   */
  private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd a HH:mm:ss");

  @JsonProperty("loginTime")
  private LocalDateTime loginTime; // 로그인 시간

  @JsonProperty("logoutTime")
  private LocalDateTime logoutTime; // 로그아웃 시간

  @JsonProperty("isLate")
  private boolean isLate; // 지각 유무

  @JsonProperty("isEarlyLeave")
  private boolean isEarlyLeave; // 기타 사유

  private String formatterLoginTime;
  private String formatterLogoutTime;

  public AttendanceRecord() {
    // 기본 생성자
  }

  @JsonCreator
  public AttendanceRecord(@JsonProperty("loginTime") LocalDateTime loginTime) {
    this.loginTime = loginTime;
    this.formatterLoginTime = loginTime.format(dtf);
    // 9시 이후 지각
    this.isLate = loginTime.toLocalTime().isAfter(LocalTime.of(9, 0));
  }

  public void setLoginTime(LocalDateTime loginTime) {
    this.loginTime = loginTime;
    this.formatterLoginTime = loginTime.format(dtf);
  }

  public LocalDateTime getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(LocalDateTime logoutTime) {
    this.logoutTime = logoutTime;
    this.formatterLogoutTime = logoutTime.format(dtf);
    // 19시 이전에는 기타사유
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

  public String getFormatterLoginTime() {
    return formatterLoginTime;
  }

  public void setFormatterLoginTime(String formatterLoginTime) {
    this.formatterLoginTime = formatterLoginTime;
  }

  public String getFormatterLogoutTime() {
    return formatterLogoutTime;
  }

  public void setFormatterLogoutTime(String formatterLogoutTime) {
    this.formatterLogoutTime = formatterLogoutTime;
  }

  @Override
  public String toString() {
    return "AttendanceRecord [loginTime=" + getFormatterLoginTime() + ", logoutTime=" + getFormatterLogoutTime() + ", isLate="
            + isLate + ", isEarlyLeave=" + isEarlyLeave + "]";
  }
}
