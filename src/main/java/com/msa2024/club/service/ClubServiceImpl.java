package com.msa2024.club.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.msa2024.club.model.Club;
import com.msa2024.club.service.ClubService;

public class ClubServiceImpl implements ClubService {
	static final String CLUBFILEPATH = "C:/Users/SungJun/git/KOSA/src/main/resources/club.json";
	
	// 1. 전체 출력 (Club JSON 파일을 로드하고 출력하는 함수)
	
	@Override
	public void printClub() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// JSON 파일을 List<ClubManager> 객체로 읽어옴
			List<Club> clubs = objectMapper.readValue(new File(CLUBFILEPATH),
					new TypeReference<List<Club>>() {
					});

			// 모든 Club 객체의 내용을 출력
			for (Club club : clubs) {
				System.out.println("소모임 번호: " + club.getClubNumber());
				System.out.println("방장 여부: " + club.isHost());
				System.out.println("음식 종류: " + club.getMenuType());
				System.out.println("방 이름: " + club.getMatchName());
				System.out.println("최대 인원: " + club.getMatchMaxSize());
				System.out.println("소모임 날짜: " + club.getMatchDate());
				System.out.println("소모임 시간: " + club.getMatchTime());
				System.out.println("소모임 장소: " + club.getMatchPlace());
				System.out.println("배달 여부: " + club.getIsDelivery());
				System.out.println(); // 빈 줄 추가로 구분
			}

		} catch (IOException e) {
			System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// 2.등록
	public void makingClub() {
		Scanner sc = new Scanner(System.in);
		ObjectMapper objectMapper = new ObjectMapper();

		// 새로운 Club 객체 생성
		System.out.println("소모임 번호를 입력하세요: ");
		String Number = sc.nextLine();
		int newClubNumber = Integer.parseInt(Number);


		try {
			// JSON 파일을 List<ClubManager> 객체로 읽어옴
			File file = new File(CLUBFILEPATH);
			List<Club> clubs = new ArrayList<>();

			if (file.exists()) {
				clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
				});

				// 중복되는 소모임 번호가 있는지 확인
				for (Club club : clubs) {					
					if ((String.valueOf(club.getClubNumber())).equals(String.valueOf(newClubNumber))) {
						System.out.println("이미 존재하는 소모임 번호입니다. 등록을 취소합니다.");
						return; // 메서드 종료
					}
				}
			}
			System.out.println("음식 종류를 입력하세요: ");
			String food = sc.nextLine();

			System.out.println("방 이름을 입력하세요: ");
			String room = sc.nextLine();

			System.out.println("최대 인원을 입력하세요: ");
			String max = sc.nextLine();
			int Max = Integer.parseInt(max);

			System.out.println("소모임 날짜를 입력하세요 (예: 2024-07-20): ");
			String day = sc.nextLine();

			System.out.println("소모임 시간을 입력하세요 (예: 12:00): ");
			String time = sc.nextLine();

			System.out.println("소모임 장소를 입력하세요: ");
			String place = sc.nextLine();

			System.out.println("배달 여부를 입력하세요 (true/false): ");
			String bedal = sc.nextLine();
			Club newClub = new Club(newClubNumber, food, room, Max, day, time, place, bedal);

			// 새로운 소모임을 목록에 추가
			clubs.add(newClub);

			// 업데이트된 목록을 JSON 파일에 저장
			objectMapper.writeValue(file, clubs);

			System.out.println("소모임이 성공적으로 추가되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 읽거나 쓰는 중 오류가 발생했습니다.");
		}
	}


	// 4. 삭제
	public void deleteClub() {
		Scanner sc = new Scanner(System.in);
		ObjectMapper objectMapper = new ObjectMapper();

		System.out.println("삭제할 소모임 번호를 입력하세요: ");
		int clubNumberToDelete = sc.nextInt();

		try {
			// JSON 파일을 List<ClubManager> 객체로 읽어옴
			File file = new File(CLUBFILEPATH);
			List<Club> clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
			});

			// Iterator를 사용하여 목록을 순회하며 소모임 삭제
			Iterator<Club> iterator = clubs.iterator();
			boolean found = false;

			while (iterator.hasNext()) {
			    Club club = iterator.next();
			    if (club.getClubNumber() == clubNumberToDelete) {
			        iterator.remove();
			        found = true;
			    }
			}

			if (found) {
			    // 삭제된 목록을 다시 JSON 파일에 씀
			    objectMapper.writeValue(file, clubs);
			    System.out.println("소모임이 성공적으로 삭제되었습니다.");
			} else {
			    System.out.println("해당 번호의 소모임이 존재하지 않습니다.");
			}

		} catch (IOException e) {
			System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}
	// 5. 참가
	public void addClub() {

		Scanner sc = new Scanner(System.in);
		ObjectMapper objectMapper = new ObjectMapper();

		// 새로운 Club 객체 생성
		System.out.println("소모임 번호를 입력하세요: ");
		String Number = sc.nextLine();
		int newClubNumber = Integer.parseInt(Number);

		try {
			// JSON 파일을 List<ClubManager> 객체로 읽어옴
			File file = new File(CLUBFILEPATH);
			List<Club> clubs = new ArrayList<>();

			if (file.exists()) {
				clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
				});

				// 중복되는 소모임 번호가 있는지 확인
				int clubNum = 0;
				for (Club club : clubs) {
					if (club.getClubNumber() == newClubNumber) {
						clubNum++;
						if (club.getMatchMaxSize() <= clubNum) {
							System.out.println("인원이 가득 찼습니다.");
							return;
						}
					}
				}
				for (Club club : clubs) {
					if (club.getClubNumber() == newClubNumber) {
						System.out.println("소모임 등록이 완료 되었습니다.");
						String food = club.getMenuType();
						String room = club.getMatchName();
						int Max = club.getMatchMaxSize();
						String day = club.getMatchDate();
						String time = club.getMatchTime();
						String place = club.getMatchPlace();
						String bedal = club.getIsDelivery();
						Club newClub = new Club(newClubNumber, food, room, Max, day, time, place, bedal);
						// 새로운 소모임을 목록에 추가
						clubs.add(newClub);
						System.out.println("소모임이 성공적으로 추가되었습니다.");
						// 업데이트된 목록을 JSON 파일에 저장
						objectMapper.writeValue(file, clubs);
						return; // 메서드 종료
					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 읽거나 쓰는 중 오류가 발생했습니다.");
		}

	}
}
