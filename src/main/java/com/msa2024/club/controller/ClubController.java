package com.msa2024.club.controller;

import java.util.Scanner;

import com.msa2024.club.service.ClubServiceImpl;
import com.msa2024.user.model.User;

public class ClubController {

	ClubServiceImpl cs = new ClubServiceImpl();
	private boolean exitRequested = false;
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n===== 소모임 =====");
			System.out.println("[메뉴 선택]");
			System.out.println("1. 전체 출력");
			System.out.println("2. 등록");
			System.out.println("3. 삭제");
			System.out.println("4. 참가");
			System.out.println("5. 참여 소모임 정보");
			System.out.println("9. 종료");
			System.out.print("메뉴를 선택하세요=>");

			String choice = sc.nextLine();

			switch (choice) {
				case "1":
					// 전체 출력 로직
					cs.printClub();
					break;
				case "2":
					// 등록 로직
					cs.makingClub();
					break;
				case "3":
					// 삭제 로직
					cs.deleteClub("yoonchaemin@daum.net");
					break;
				case "4":
					// 참가 로직
					cs.addClub("yoonchaemin@daum.net");
					break;
				case "5":
					// 참여 소모임 정보
					cs.printMyClub("yoonchaemin@daum.net");
					break;
				case "9":
					System.out.println("\n[종료]");
					System.out.println("프로그램을 종료합니다.");
					exitRequested = true;
					return;
				default:
					System.out.println("\n없는 메뉴입니다. 다시 선택하세요.");
					break;
			}
		}
	}

	public boolean isExitRequested() {
		return exitRequested;
	}
}
