package com.msa2024.reservation.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.reservation.model.Reservation;
import com.msa2024.util.GenericFileUtil;

public class ReservationService {
    private List<Reservation> reservations;
    private GenericFileUtil<Reservation> fileUtil;
    private static final String RESERVATIONS_FILE = "reservations.json";

    // 생성자: 파일 경로를 받아서 초기화하고 예약 정보를 파일에서 로드
    public ReservationService(String basePath) {
        this.fileUtil = new GenericFileUtil<>(basePath);
        this.reservations = new ArrayList<>();
        loadReservationsFromFile();
    }

    // 새로운 예약을 추가하는 메서드
    public void addReservation(Scanner sc) {
        System.out.println("\n일주일 단위로 예약이 가능합니다.");
        System.out.print("\n회의실 번호를 입력하세요(1~3): ");
        int roomSeq = sc.nextInt();
        sc.nextLine();
        System.out.print("이메일을 입력하세요: ");
        String email = sc.nextLine();
        System.out.print("전화번호를 입력하세요 (예 01012345678): ");
        String telePhone = sc.nextLine();
        System.out.print("예약 날짜를 입력하세요 (예 2023-07-01): ");
        String reservationDate = sc.nextLine();
        System.out.print("예약 시작 시간을 입력하세요 (9시~ 18시 사이 (예 09:00)): ");
        String startTime = sc.nextLine();

        // 현재 날짜와 예약 날짜 비교
        LocalDate today = LocalDate.now();
        // 날짜 포맷터를 지정 (yyyy-MM-dd 형식)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 예약 날짜 문자열을 LocalDate 객체로 변환
        LocalDate reservationLocalDate = LocalDate.parse(reservationDate, formatter);
        // 시간의 단위를 제공하여 시간의 양을 측정
        long daysBetween = ChronoUnit.DAYS.between(today, reservationLocalDate);

        if (daysBetween > 7 || daysBetween < 0) {
            System.out.println("\n예약 가능 기간은 오늘날짜로부터 7일 이내입니다.");
            return;
        }

        // 예약 중복 여부 확인
        if (isReservationConflict(roomSeq, reservationDate, startTime)) {
            System.out.println("\n해당 날짜와 시간에 이미 예약이 존재합니다.");
            return;
        }
        
        // 새로운 예약 객체 생성 및 추가
        Reservation reservation = new Reservation(roomSeq, email, telePhone, reservationDate, startTime);
        reservations.add(reservation);
        saveReservationsToFile();
        System.out.println("\n예약이 추가되었습니다.");
    }

        // 예약 중복 여부를 확인하는 메서드
        private boolean isReservationConflict(int roomSeq, String reservationDate, String startTime) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoomSeq() == roomSeq &&
                reservation.getReservationDate().equals(reservationDate) &&
                reservation.getStartTime().equals(startTime)) {
                return true;
            }
        }
        return false;
    }

    // 예약 목록을 출력하는 메서드
    public void viewReservations() {
        List<Reservation> reservationList = fileUtil.readFromFileWithJackson(RESERVATIONS_FILE, new TypeReference<List<Reservation>>() {});

        // 예약 목록을 날짜와 시간순으로 정렬
        Collections.sort(reservationList, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation r1, Reservation r2) {
                int dateComparison = r1.getReservationDate().compareTo(r2.getReservationDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return r1.getStartTime().compareTo(r2.getStartTime());
            }
        });

        System.out.println("----------------------------------------------------------");
        System.out.println("|      날짜      |   시간    |   회의룸    |");
        System.out.println("----------------------------------------------------------");
        for (Reservation reservation : reservationList) {
            System.out.println("|   " + reservation.getReservationDate() + "   |      " + reservation.getStartTime() + "     |     " + reservation.getRoomSeq() + "    |");
        }
        System.out.println("----------------------------------------------------------");
    }

    // 이메일로 예약을 필터링하는 메서드
    public List<Reservation> getReservationsByEmail(String email) {
        List<Reservation> filteredReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getEmail().equals(email)) {
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }

    // 기존 예약을 수정하는 메서드
    public void reReservation(Scanner sc) {
        System.out.print("\n수정할 사람의 이메일을 입력하세요: ");
        String email = sc.nextLine();
        System.out.print("수정할 회의실 번호를 입력하세요(1~3): ");
        int roomSeq = sc.nextInt();
        sc.nextLine();

        System.out.print("수정할 회의실 날짜를 입력하세요: ");
        String date = sc.nextLine();

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getEmail().equals(email) && reservation.getRoomSeq() == roomSeq
                    && reservation.getReservationDate().equals(date)) {
                System.out.print("이메일을 입력하세요: ");
                String newEmail = sc.nextLine();
                System.out.print("새 회의실 번호를 입력하세요: ");
                int newRoom = sc.nextInt();
                sc.nextLine();
                System.out.print("전화번호를 입력하세요: ");
                String newTelePhone = sc.nextLine();
                System.out.print("새 예약 날짜를 입력하세요 (예: 2023-07-01): ");
                String newDate = sc.nextLine();
                System.out.print("새 예약 시작 시간을 입력하세요 (예: 09:00): ");
                String newStartTime = sc.nextLine();

                reservation.setEmail(newEmail);
                reservation.setRoomSeq(newRoom);
                reservation.setTelePhone(newTelePhone);
                reservation.setReservationDate(newDate);
                reservation.setStartTime(newStartTime);
                System.out.println("수정되었습니다.");
                saveReservationsToFile();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("해당 예약을 찾을 수 없습니다.");
        }
    }

    // 예약을 삭제하는 메서드
    public boolean deleteReservation(Scanner sc) {
        System.out.print("\n삭제할 사람의 이메일을 입력해주세요: ");
        String email = sc.nextLine();
        System.out.print("예약된 날짜를 입력해주세요 (예: 2023-07-01): ");
        String date = sc.nextLine();
        System.out.print("예약된 시간을 입력해주세요 (예: 09:00): ");
        String time = sc.nextLine();

        for (Reservation reservation : reservations) {
            if (reservation.getEmail().equals(email) &&
                    reservation.getReservationDate().equals(date) &&
                    reservation.getStartTime().equals(time)) {

                System.out.println("정말 삭제하시겠습니까?");
                boolean whileLoop = true;
                while (whileLoop) {
                    System.out.println("1. 삭제 O");
                    System.out.println("2. 삭제 X");
                    System.out.println("번호를 선택해 주세요=> ");
                    String choose = sc.nextLine();
                    switch (choose) {
                        case "1":
                            reservations.remove(reservation);
                            saveReservationsToFile();
                            System.out.println("\n예약이 삭제되었습니다.");
                            whileLoop = false;
                            break;
                        case "2":
                            System.out.println("\n예약을 삭제하지 않습니다.");
                            whileLoop = false;
                            break;
                        default:
                            System.out.println("\n잘못된 입력입니다. 다시 선택해주세요.");
                    }
                }
                return true;
            }
        }
        System.out.println("\n해당 예약을 찾을 수 없습니다.");
        return false;
    }

    // 특정 사용자의 예약 목록을 조회하는 메서드
    public void myReservation(Scanner sc) {
        System.out.print("\n조회할 사람의 이메일을 입력하세요: ");
        String email = sc.nextLine();
        List<Reservation> userReservations = getReservationsByEmail(email);

        if (userReservations.isEmpty()) {
            System.out.println("\n해당 이메일의 예약이 없습니다.");
        } else {
            // 예약 목록을 날짜, 시간, 회의룸 순서로 정렬
            Collections.sort(userReservations, new Comparator<Reservation>() {
                @Override
                public int compare(Reservation r1, Reservation r2) {
                    int dateComparison = r1.getReservationDate().compareTo(r2.getReservationDate());
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    int timeComparison = r1.getStartTime().compareTo(r2.getStartTime());
                    if (timeComparison != 0) {
                        return timeComparison;
                    }
                    return Integer.compare(r1.getRoomSeq(), r2.getRoomSeq());
                }
            });

            // 예약 목록 출력
            System.out.println("\n나의 예약 목록");
            System.out.println("------------------------------------------------------");
            System.out.println("|      날짜      |       시간      |       회의룸     |");
            System.out.println("------------------------------------------------------");
            for (Reservation reservation : userReservations) {
                System.out.println("|   " + reservation.getReservationDate() + "   | " + reservation.getStartTime() + "  |     " + reservation.getRoomSeq() + "    |");
            }
            System.out.println("------------------------------------------------------");
        }
    }

    // 예약 정보를 파일에 저장하는 메서드
    private void saveReservationsToFile() {
        fileUtil.writeToFileWithJackson(RESERVATIONS_FILE, reservations);
    }

    // 파일에서 예약 정보를 로드하는 메서드
    private void loadReservationsFromFile() {
        List<Reservation> readReservations = fileUtil.readFromFileWithJackson(RESERVATIONS_FILE, new TypeReference<List<Reservation>>() {});
        if (readReservations == null) {
            this.reservations = new ArrayList<>();
        } else {
            this.reservations = new ArrayList<>(readReservations);
        }
    }
}
