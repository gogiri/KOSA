
package com.msa2024.user;

import java.time.LocalDate;

public class User {
  
  private String email;
  private String name;
  private String password;
  private Role role;
  private LocalDate blockDate;
  //private List<AttendanceRecord> attendanceRecords;

  

  public User(String email, String name, String password, Role role, LocalDate blockDate) {
      this.email = email;
      this.name = name;
      this.password = password;
      this.role = role;
      this.blockDate = blockDate;
      
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



  @Override
  public String toString() {
    return "User [email=" + email + ", name=" + name + ", password=" + password + ", role=" + role
        + ", blockDate=" + blockDate + "]";
  }
  
  


}



