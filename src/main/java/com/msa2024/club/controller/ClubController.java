package com.msa2024.club.controller;

import java.util.Scanner;

import com.msa2024.club.service.ClubServiceImpl;
import com.msa2024.user.model.User;

public class ClubController {
	User loggedInUser;								// email을 불러오기 위한 loggeInUser
	ClubServiceImpl cs = new ClubServiceImpl();
	
	
	// loggerdInUser 불러온 후 객체에 저장
	public ClubController(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	// 메뉴 전환을 위한 boolean
	private boolean exitRequested = false;
	
	// 소모임 메뉴 실행
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n===== 모임 =====");
			System.out.println("[메뉴 선택]");
			System.out.println("1. 전체 출력");
			System.out.println("2. 등록");
			System.out.println("3. 삭제");
			System.out.println("4. 참가");
			System.out.println("5. 참여 모임 정보");
			System.out.println("9. 종료");
			System.out.println("메뉴를 선택하세요=>");
			

			String choice = sc.nextLine();

			switch (choice) {
				case "1":
					// 1. 전체 출력 로직
					cs.printClub();
					break;
				case "2":
					// 2. 등록 로직
					cs.makingClub(loggedInUser.getEmail());
					break;
				case "3":
					// 3. 삭제 로직
					cs.deleteClub(loggedInUser.getEmail());
					break;
				case "4":
					// 4. 참가 로직
					cs.addClub(loggedInUser.getEmail());
					break;
				case "5":
					// 5. 참여 모임 정보
					cs.printMyClub(loggedInUser.getEmail());
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
