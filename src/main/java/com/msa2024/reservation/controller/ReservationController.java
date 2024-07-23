package com.msa2024.reservation.controller;

import java.util.Scanner;

import com.msa2024.reservation.service.ReservationService;

public class ReservationController {
    private boolean exitRequested = false;
    private ReservationService reservationService;

    public ReservationController(String basePath) {
        this.reservationService = new ReservationService(basePath);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=====[선택]=====");
                System.out.println("\n1.전체조회");
                System.out.println("2.예약");
                System.out.println("3.수정");
                System.out.println("4.삭제");
                System.out.println("5.나의 예약");
                System.out.println("6.뒤로 가기");
                System.out.println("\n번호를 선택하세요=> ");
                String menu = sc.nextLine();

                switch (menu) {
                    case "1":
                        System.out.println("\n[전체조회]");
                        reservationService.viewReservations();
                        break;
                    case "2":
                        System.out.println("\n[예약]");
                        reservationService.addReservation(sc);
                        break;
                    case "3":
                        System.out.println("\n[수정]");
                        reservationService.reReservation(sc);
                        break;
                    case "4":
                        System.out.println("\n[삭제]");
                        reservationService.deleteReservation(sc);
                        break;
                    case "5":
                        System.out.println("\n[나의 예약]");
                        reservationService.myReservation(sc);
                        break;
                    case "6":
                        System.out.println("\n[종료]");
                        exitRequested = true;
                        return;
                    default:
                        System.out.println("\n1 ~ 6번 중 선택해 주세요.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExitRequested() {
        return exitRequested;
    }
}
