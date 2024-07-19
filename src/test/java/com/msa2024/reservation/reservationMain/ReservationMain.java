package com.msa2024.reservation.reservationMain;

import java.util.Scanner;
import com.msa2024.reservation.controller.ReservationController;

public class ReservationMain {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\KOSA\\git\\KOSA\\src\\test\\resources\\reservations.json";
        ReservationController controller = new ReservationController(filePath);
        Scanner sc = new Scanner(System.in);

        boolean whileLoop = true;
        
        while (whileLoop) {
            try {
                System.out.println("\n=====[선택]=====");
                System.out.println("\n1.전체조회");
                System.out.println("2.예약");
                System.out.println("3.수정");
                System.out.println("4.삭제");
                System.out.println("5.나의 예약");
                System.out.println("6.종료");
                System.out.print("\n번호를 선택하세요=> ");
                String menu = sc.nextLine();

                switch (menu) {
                    case "1":
                        System.out.println("\n[전체조회]");
                        controller.viewReservations();
                        break;
                    case "2":
                        System.out.println("\n[예약]");
                        controller.addReservation(sc);
                        break;
                    case "3":
                        System.out.println("\n[수정]");
                        controller.reReservation(sc);
                        break;
                    case "4":
                        System.out.println("\n[삭제]");
                        controller.deleteReservation(sc);
                        break;
                    case "5":
                        System.out.println("\n[나의 예약]");
                        controller.myReservation(sc);
                        break;
                    case "6":
                        System.out.println("\n[종료]");
                        whileLoop = false;
                        break;
                    default:
                        System.out.println("\n1 ~ 6번 중 선택해 주세요.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sc.close();
        System.out.println("\n=====Good Bye=====");
        System.exit(0);
    }
}