package com.msa2024.club.controller;

import java.util.Scanner;

import com.msa2024.club.service.ClubServiceImpl;

public class ClubController {
	public void run() {
		System.out.println("\n===== 소모임 =====");

		// 클럽 메뉴 열기
		ClubServiceImpl cm = new ClubServiceImpl();
		Scanner scClub = new Scanner(System.in);

		// Club 객체 예시 생성
//		ClubManager club = new ClubManager();
//		club.setClubNumber(0);
//		club.setHost(true);
//		club.setMenuType("한식");
//		club.setMatchName("Example");
//		club.setMatchMaxSize(0);
//		club.setMatchDate("2024-07-20");
//		club.setMatchTime("12:00");
//		club.setMatchPlace("KOSA");
//		club.setDelivery("false");
//
//		// JSON 파일 생성
//		cm.createJsonFile(club, cm.getFilepath());

		boolean whileLoop = true;
		while (whileLoop) {
			try {
				System.out.println("[메뉴 선택]");
				System.out.println("1. 전체 출력");
				System.out.println("2. 등록");
				System.out.println("3. 수정");
				System.out.println("4. 삭제");
				System.out.println("5. 참가");
				System.out.println("9. 종료");
				System.out.print("메뉴를 선택하세요=>");
				String menu = scClub.nextLine();

				switch (menu) {
				case "1":
					System.out.println("\n[전체 출력]");
					cm.printClub();
					break;

				case "2":
					System.out.println("\n[등록]");
					cm.makingClub();
					break;

				case "3":
					System.out.println("\n[삭제]");
					cm.deleteClub();
					break;

				case "4":
					System.out.println("\n[참가]");
					cm.addClub();
					break;

				case "9":
					System.out.println("\n[종료]");
					System.out.println("프로그램을 종료합니다.");
					whileLoop = false;
					break;
				default:
					System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
					break;

				}
			} catch (Exception e) {

			}
		}
		scClub.close();
		System.out.println("\n===== END =====");
		
	}
}
