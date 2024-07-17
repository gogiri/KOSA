package com.msa2024.club;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClubMatcher {
	private String filePath = " ";
	
	private List<Club> clubs; // 클럽 리스트
	private static int nextClubNumber=1; // 클럽 번호 1부터 시작

	public ClubMatcher() {
		this.clubs = new ArrayList<>(); // 클럽 매치 생성
	}

	public void addClub(Club club) {
		clubs.add(club); // 클럽 리스트에 클럽 추가
		nextClubNumber++; // 개설 번호 +1
	}

	// get 클럽 넘버
	public int getNextClubNumber() {
		return nextClubNumber;
	}

	// get 클럽 리스트
	public List<Club> getClubs() {
		return clubs;
	}

	// get 클럽 넘버&리스트
	public Club getClubByNumber(int clubNumber) {
		for (Club club : clubs) {
			if (club.getClubNumber() == clubNumber) {
				return club;
			}
		}
		return null;
	}

	// 1. 클럽 목록 조회
	public void printClubs() {
		System.out.println("전체 클럽 목록:");
		for (Club club : clubs) {
			System.out.println("No." + club.getClubNumber() + " 방장: " + club.getHost() + " 메뉴: " + club.getMenuType()
					+ " 제목: " + club.getMatchName() + " 인원: " + club.getMatchMaxSize() + " 날짜: " + club.getMatchDate()
					+ " 시간" + club.getMatchTime() + " 장소: " + club.getMatchPlace() + " 배달: " + club.getIsDelivery());
		}
		System.out.println();
	}

	// 해당 번호 클럽 조회
	public void printClubDetails(int clubNumber) {
		Club club = getClubByNumber(clubNumber);
		if (club != null) {
			System.out.println("No." + club.getClubNumber() + " 방장: " + club.getHost() + " 메뉴: " + club.getMenuType()
					+ " 제목: " + club.getMatchName() + " 인원: " + club.getMatchMaxSize() + " 날짜: " + club.getMatchDate()
					+ " 시간" + club.getMatchTime() + " 장소: " + club.getMatchPlace() + " 배달: " + club.getIsDelivery());

			for (Participant participant : club.getParticipants()) {
				System.out.println("- " + participant);
			}
			System.out.println();
		} else {
			System.out.println("해당 번호의 클럽이 존재하지 않습니다.");
		}

	}

	// 2.등록
	public void ClubMaking() {

		// 방을 만들어봅시다.
		Scanner scanner = new Scanner(System.in);
		ClubMatcher clubMatcher = new ClubMatcher();

		// 0. 클럽 생성 & 방 번호
		System.out.println("클럽을 생성합니다.");
		int clubNumber = clubMatcher.getNextClubNumber();

		// 1. 호스트 이름 -> 자동으로 생성되게 바꾸기
		System.out.println("호스트 이름을 입력하세요:");
		String hostName = scanner.nextLine();
		Host host = new Host(hostName);

		// 2. 음식 종류 입력
		System.out.println("음식 종류를 입력하세요:");
		String menuType = scanner.nextLine();

		// 3. 방 이름 입력
		System.out.println("방 이름을 입력하세요:");
		String matchName = scanner.nextLine();

		// 4. 방 최대 인원 입력 -> 최소 최대값 정하기
		System.out.println("최대 인원을 입력하세요:");
		String matchMaxSizeString = scanner.nextLine();
		int matchMaxSize = Integer.parseInt(matchMaxSizeString);

		// 5. 약속 날짜 입력 -> 연도 지우고 형태 새로
		System.out.println("매칭 날짜를 입력하세요 (YYYY-MM-DD):");
		String matchDate = scanner.nextLine();

		// 6. 약속 시간 입력 -> 시간 형태 편하게
		System.out.println("약속 시간을 입력하세요 (HH:MM):");
		String matchTime = scanner.nextLine();

		// 7. 약속 장소 입력
		System.out.println("약속 장소를 입력하세요 :");
		String matchPlace = scanner.nextLine();

		// 8. 배달 여부 입력 -> y/n 1/0 등 간편하게
		System.out.println("배달 여부를 입력하세요 (true/false):");
		boolean isDelivery = scanner.nextBoolean();

		scanner.nextLine(); // Consume newline

		Club club = host.createClub(host, menuType, matchName, matchMaxSize, matchDate, matchTime, matchPlace,
				isDelivery);
		clubMatcher.addClub(club);
	}

	//
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	public void setNextClubNumber(int nextClubNumber) {
		this.nextClubNumber = nextClubNumber;
	}

	// 3. 수정하기
	public void ClubRetouch() {

		// 방을 만들어봅시다.
		Scanner scanner = new Scanner(System.in);
		ClubMatcher clubMatcher = new ClubMatcher();

		// 0. 클럽 생성 & 방 번호
		System.out.println("클럽을 생성합니다.");
		int clubNumber = clubMatcher.getNextClubNumber();

		// 1. 호스트 이름 -> 자동으로 생성되게 바꾸기
		System.out.println("호스트 이름을 입력하세요:");
		String hostName = scanner.nextLine();
		Host host = new Host(hostName);

		// 2. 음식 종류 입력
		System.out.println("음식 종류를 입력하세요:");
		String menuType = scanner.nextLine();

		// 3. 방 이름 입력
		System.out.println("방 이름을 입력하세요:");
		String matchName = scanner.nextLine();

		// 4. 방 최대 인원 입력 -> 최소 최대값 정하기
		System.out.println("최대 인원을 입력하세요:");
		String matchMaxSizeString = scanner.nextLine();
		int matchMaxSize = Integer.parseInt(matchMaxSizeString);

		// 5. 약속 날짜 입력 -> 연도 지우고 형태 새로
		System.out.println("매칭 날짜를 입력하세요 (YYYY-MM-DD):");
		String matchDate = scanner.nextLine();

		// 6. 약속 시간 입력 -> 시간 형태 편하게
		System.out.println("약속 시간을 입력하세요 (HH:MM):");
		String matchTime = scanner.nextLine();

		// 7. 약속 장소 입력
		System.out.println("약속 장소를 입력하세요 :");
		String matchPlace = scanner.nextLine();

		// 8. 배달 여부 입력 -> y/n 1/0 등 간편하게
		System.out.println("배달 여부를 입력하세요 (true/false):");
		boolean isDelivery = scanner.nextBoolean();

		scanner.nextLine(); // Consume newline

		Club club = host.createClub(host, menuType, matchName, matchMaxSize, matchDate, matchTime, matchPlace,
				isDelivery);
		clubMatcher.addClub(club);
	}
}
