package com.msa2024.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceRecord {

  private LocalDateTime loginTime;
  private LocalDateTime logoutTime;
  private boolean earlyLeave;
  private boolean late;

  @JsonCreator
  public AttendanceRecord(
          @JsonProperty("loginTime") LocalDateTime loginTime,
          @JsonProperty("logoutTime") LocalDateTime logoutTime,
          @JsonProperty("earlyLeave") boolean earlyLeave,
          @JsonProperty("late") boolean late
  ) {
    this.loginTime = loginTime;
    this.logoutTime = logoutTime;
    this.earlyLeave = earlyLeave;
    this.late = late;
  }

  public AttendanceRecord(LocalDateTime loginTime) {
    this.loginTime = loginTime;
  }

  public LocalDateTime getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(LocalDateTime loginTime) {
    this.loginTime = loginTime;
  }

  public LocalDateTime getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(LocalDateTime logoutTime) {
    this.logoutTime = logoutTime;
  }

  public boolean isEarlyLeave() {
    return earlyLeave;
  }

  public void setEarlyLeave(boolean earlyLeave) {
    this.earlyLeave = earlyLeave;
  }

  public boolean isLate() {
    return late;
  }

  public void setLate(boolean late) {
    this.late = late;
  }

  public String getFormatterLoginTime() {
    return loginTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd a HH:mm:ss"));
  }

  public String getFormatterLogoutTime() {
    if (logoutTime == null) {
      return null;
    }
    return logoutTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd a HH:mm:ss"));
  }

  @Override
  public String toString() {
    return "AttendanceRecord{" +
            "loginTime=" + loginTime +
            ", logoutTime=" + logoutTime +
            ", earlyLeave=" + earlyLeave +
            ", late=" + late +
            '}';
  }
}
