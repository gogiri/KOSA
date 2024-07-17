package com.msa2024.admin.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
public class AdminController {
    //    private UserManager userManager;
//    private ReservationManager reservationManager;
//    private ClubManager clubManager;
//
//    public AdminManager(UserManager userManager, ReservationManager reservationManager, ClubManager clubManager) {
//        this.userManager = userManager;
//        this.reservationManager = reservationManager;
//        this.clubManager = clubManager;
//    }
//
//    public void listAllUsers() {
//        HashMap<String, User> users = userManager.getAllUsers();
//        for (User user : users.values()) {
//            System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 역할: " + user.getRole() + ", 차단된 시간: " + user.getBlockedUntil());
//        }
//    }
//
//    public void listAllReservations() {
//        ArrayList<Reservation> reservations = reservationManager.listReservations();
//        for (Reservation reservation : reservations) {
//            System.out.println("회의실: " + reservation.getRoomSeq() + ", 예약 시간: " + reservation.getReservationTime() + ", 사용자: " + reservation.getUser() + ", 취소 여부: " + reservation.isCancelled() + ", 완료 여부: " + reservation.isCompleted());
//        }
//    }
//
//    public void listAllClubs() {
//        ArrayList<Club> clubs = clubManager.listClubs();
//        for (Club club : clubs) {
//            System.out.println("소모임 이름: " + club.getName() + ", 설명: " + club.getDescription() + ", 멤버: " + club.getMembers());
//        }
//    }
//
//    public void blockUser(String email, int hours) {
//        User user = userManager.getUser(email);
//        if (user != null) {
//            Date blockedUntil = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(hours));
//            user.setBlockedUntil(blockedUntil);
//            userManager.saveUsersToFile();
//            System.out.println("사용자 " + email + "이 " + blockedUntil + "까지 차단되었습니다.");
//        } else {
//            System.out.println("사용자를 찾을 수 없습니다.");
//        }
//    }
//
//    public void checkForBlockedUsers() {
//        HashMap<String, User> users = userManager.getAllUsers();
//        for (User user : users.values()) {
//            if (user.getBlockedUntil() != null && new Date().after(user.getBlockedUntil())) {
//                user.setBlockedUntil(null);
//                userManager.saveUsersToFile();
//                System.out.println("사용자 " + user.getEmail() + "의 차단이 해제되었습니다.");
//            }
//        }
//    }
//
//    public void updateUser(String email, String newName, String newPassword, String newRole) {
//        User user = userManager.getUser(email);
//        if (user != null) {
//            if (newName != null && !newName.isEmpty()) user.setName(newName);
//            if (newPassword != null && !newPassword.isEmpty()) user.setPassword(newPassword);
//            if (newRole != null && !newRole.isEmpty()) user.setRole(newRole);
//            userManager.saveUsersToFile();
//            System.out.println("사용자 정보가 업데이트되었습니다.");
//        } else {
//            System.out.println("사용자를 찾을 수 없습니다.");
//        }
//    }
//
//    public void listBlacklistedUsers() {
//        HashMap<String, User> users = userManager.getAllUsers();
//        for (User user : users.values()) {
//            if (user.getBlockedUntil() != null) {
//                System.out.println("이메일: " + user.getEmail() + ", 이름: " + user.getName() + ", 차단된 시간: " + user.getBlockedUntil());
//            }
//        }
//}
}
