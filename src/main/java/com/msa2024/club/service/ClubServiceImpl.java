package com.msa2024.club.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.msa2024.club.model.Club;
import com.msa2024.club.service.ClubService;
import com.msa2024.user.model.UserManager;
import com.msa2024.user.service.UserServiceImpl;

public class ClubServiceImpl implements ClubService {

	public ClubServiceImpl() {

	}

	static final String CLUBFILEPATH = "src/main/java/resources/club.json";

	
	// 1. 전체 출력 (Club JSON 파일을 로드하고 출력하는 함수)
	@Override
	public void printClub() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// JSON 파일을 List<Club> 객체로 읽어옴
			List<Club> clubs = objectMapper.readValue(new File(CLUBFILEPATH), new TypeReference<List<Club>>() {
			});

			// 소모임 번호를 추적하기 위한 Set
			Set<Integer> printedClubNumbers = new HashSet<>();

			// 모든 Club 객체의 내용을 출력
			for (Club club : clubs) {
				if (!printedClubNumbers.contains(club.getClubNumber())) {
					System.out.println("모임 번호: " + club.getClubNumber());
					System.out.println("음식 종류: " + club.getMenuType());
					System.out.println("방 이름: " + club.getMatchName());
					System.out.println("최대 인원: " + club.getMatchMaxSize());
					System.out.println("모임 날짜: " + club.getMatchDate());
					System.out.println("모임 시간: " + club.getMatchTime());
					System.out.println("모임 장소: " + club.getMatchPlace());
					System.out.println("배달 여부: " + club.getIsDelivery());
					System.out.println(); // 빈 줄 추가로 구분

					// 현재 소모임 번호를 Set에 추가
					printedClubNumbers.add(club.getClubNumber());
				}
			}

		} catch (IOException e) {
			System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// 2.등록
	public void makingClub(String userEmail, Scanner scanner) {
		Scanner sc = scanner;
		ObjectMapper objectMapper = new ObjectMapper();

		// 새로운 Club 객체 생성
		System.out.println("모임 번호를 입력하세요: ");
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
						System.out.println("이미 존재하는 모임 번호입니다. 등록을 취소합니다.");
						return; // 메서드 종료
					}
				}
				// 중복되는 이메일이 있는지 확인
				for (Club club : clubs) {
					if (club.getEmail().equals(userEmail)) {
						System.out.println("이미 존재하는 이메일입니다. 등록을 취소합니다.");
						System.out.println("해당 이메일이 속한 모임 번호: " + club.getClubNumber());
						return; // 메서드 종료
					}
				}
			}
			System.out.println("음식 종류를 입력하세요: ");
			String menuType = sc.nextLine();

			System.out.println("방 이름을 입력하세요: ");
			String matchName = sc.nextLine();

			System.out.println("최대 인원을 입력하세요: ");
			String max = sc.nextLine();
			int matchMaxSize = Integer.parseInt(max);

			System.out.println("모임 시간을 입력하세요 (예: 12:00): ");
			String matchTime = sc.nextLine();

			System.out.println("모임 장소를 입력하세요: ");
			String matchPlace = sc.nextLine();

			System.out.println("배달 여부를 입력하세요 (true/false): ");
			String isDelivery = sc.nextLine();
			
			Club newClub = new Club(newClubNumber, userEmail, menuType, matchName, matchMaxSize, matchTime, matchPlace, isDelivery);

			// 새로운 소모임을 목록에 추가
			clubs.add(newClub);

			// 업데이트된 목록을 JSON 파일에 저장
			objectMapper.writeValue(file, clubs);

			System.out.println("모임이 성공적으로 추가되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 읽거나 쓰는 중 오류가 발생했습니다.");
		}
	}

	// 3. 삭제
	public void deleteClub(String userEmail, Scanner scanner) {
		Scanner sc = scanner;
		ObjectMapper objectMapper = new ObjectMapper();

		System.out.println("삭제할 모임 번호를 입력하세요: ");
		int clubNumberToDelete = sc.nextInt();

		try {
			// JSON 파일을 List<Club> 객체로 읽어옴
			File file = new File(CLUBFILEPATH);
			List<Club> clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
			});

			// Iterator를 사용하여 목록을 순회하며 소모임 삭제
			Iterator<Club> iterator = clubs.iterator();
			boolean found = false;

			while (iterator.hasNext()) {
				Club club = iterator.next();
				if (club.getClubNumber() == clubNumberToDelete && club.getEmail().equals(userEmail)) {
					iterator.remove();
					found = true;
				}
			}

			if (found) {
				// 삭제된 목록을 다시 JSON 파일에 씀
				objectMapper.writeValue(file, clubs);
				System.out.println("모임이 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("해당 번호의 모임이 존재하지 않거나, 삭제 권한이 없습니다.");
			}

		} catch (IOException e) {
			System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//4. 참가
	public void addClub(String userEmail, Scanner scanner) {

		Scanner sc = scanner;
		ObjectMapper objectMapper = new ObjectMapper();

		// 참가할 소모임 번호 입력
		System.out.println("소모임 번호를 입력하세요: ");
		String number = sc.nextLine();
		int clubNumberToJoin = Integer.parseInt(number);

		try {
			// JSON 파일을 List<Club> 객체로 읽어옴
			File file = new File(CLUBFILEPATH);
			List<Club> clubs = new ArrayList<>();

			if (file.exists()) {
				clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
				});

				// 중복되는 소모임 번호가 있는지 확인 및 이메일 중복 확인
				int currentMemberCount = 0;
				boolean clubFound = false;
				for (Club club : clubs) {
					if (club.getClubNumber() == clubNumberToJoin) {
						clubFound = true;
						currentMemberCount++;
					}
					if (club.getEmail().equals(userEmail)) {
						System.out.println("이미 모임에 참가한 이메일입니다. 참가를 취소합니다.");
						return; // 메서드 종료
					}
				}

				if (!clubFound) {
					System.out.println("해당 번호의 모임이 존재하지 않습니다.");
					return; // 메서드 종료
				}

				// 소모임의 최대 인원 확인
				for (Club club : clubs) {
					if (club.getClubNumber() == clubNumberToJoin && club.getMatchMaxSize() <= currentMemberCount) {
						System.out.println("인원이 가득 찼습니다.");
						return; // 메서드 종료
					}
				}

				// 소모임에 참가
				for (Club club : clubs) {
					if (club.getClubNumber() == clubNumberToJoin) {
						System.out.println("모임 등록이 완료 되었습니다.");
						String menuType = club.getMenuType();
						String matchName = club.getMatchName();
						int matchMaxSize = club.getMatchMaxSize();
						String matchTime = club.getMatchTime();
						String matchPlace = club.getMatchPlace();
						String isDelivery = club.getIsDelivery();
						Club newClub = new Club(clubNumberToJoin, userEmail, menuType, matchName, matchMaxSize, matchTime, matchPlace, isDelivery);

						// 새로운 참가자를 목록에 추가
						clubs.add(newClub);
						System.out.println("모임에 성공적으로 참가되었습니다.");

						// 업데이트된 목록을 JSON 파일에 저장
						objectMapper.writeValue(file, clubs);
						return; // 메서드 종료
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 읽거나 쓰는 중 오류가 발생했습니다.");
		} finally {
		}
	}

	// 5. 나의 모임 출력
	public void printMyClub(String userEmail) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        // JSON 파일을 List<Club> 객체로 읽어옴
	        List<Club> clubs = objectMapper.readValue(new File(CLUBFILEPATH), new TypeReference<List<Club>>() {});

	        boolean found = false;

	        // 모든 Club 객체의 내용을 탐색하여 이메일이 일치하는 소모임 출력
	        for (Club club : clubs) {
	            if (club.getEmail().equals(userEmail)) {
	                System.out.println("모임 번호: " + club.getClubNumber());
	                System.out.println("음식 종류: " + club.getMenuType());
	                System.out.println("방 이름: " + club.getMatchName());
	                System.out.println("최대 인원: " + club.getMatchMaxSize());
	                System.out.println("모임 날짜: " + club.getMatchDate());
	                System.out.println("모임 시간: " + club.getMatchTime());
	                System.out.println("모임 장소: " + club.getMatchPlace());
	                System.out.println("배달 여부: " + club.getIsDelivery());
	                System.out.println(); // 빈 줄 추가로 구분

	                found = true;
	            }
	        }

	        if (!found) {
	            System.out.println("해당 이메일로 등록된 모임이 없습니다.");
	        }

	    } catch (IOException e) {
	        System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	// 6. 소모임 목록 전체 삭제
	public void deleteAllClubs() {
	    ObjectMapper objectMapper = new ObjectMapper();

	    try {
	        // JSON 파일을 List<Club> 객체로 읽어옴
	        File file = new File(CLUBFILEPATH);
	        List<Club> clubs = objectMapper.readValue(file, new TypeReference<List<Club>>() {
	        });

	        // 목록을 비웁니다.
	        clubs.clear();

	        // 비워진 목록을 다시 JSON 파일에 씀
	        objectMapper.writeValue(file, clubs);
	        System.out.println("모든 모임이 성공적으로 삭제되었습니다.");

	    } catch (IOException e) {
	        System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}
