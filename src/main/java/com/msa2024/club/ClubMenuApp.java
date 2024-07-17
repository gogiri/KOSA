package com.msa2024.club;

import java.util.Scanner;

public class ClubMenuApp {

	public static void main(String[] args) {

		System.out.println("===== 소모임 =====");
		
		// 클럽 메뉴 열기
		ClubMatcher cm = new ClubMatcher();
		Scanner sc = new Scanner(System.in);

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
				String menu = sc.nextLine();

				switch (menu) {
				case "1":
					System.out.println("\n[전체 출력]");
					cm.printClubs();
					break;
					
				case "2":
					System.out.println("\n[등록]");
					cm.ClubMaking();
					break;
					
				case "3":
					System.out.println("\n[수정]");
					cm.ClubRetouch();
					break;
					
//				case "4":
//					System.out.println("\n[삭제]");
//					cm.deletePerson();
//					break;
					
//				case "5":
//					System.out.println("\n[참가]");
//					cm.deletePerson();
//					break;

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
		sc.close();
		System.out.println("\n===== END =====");

	}
}
