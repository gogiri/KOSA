package com.msa2024.club;

import java.util.Scanner;

public class ParticipantMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClubMatcher clubMatcher = new ClubMatcher();

        // 여기에서 ClubMatcher 인스턴스에 클럽을 추가하는 코드를 추가할 수 있습니다.
        // 이 예제에서는 클럽을 미리 추가한다고 가정합니다.
        clubMatcher.addClub(new Club(1, new Host("Host1"), "메뉴", "방이름", 1,"장소","1","1", false));
        
        System.out.println("몇 명의 참여자를 입력하시겠습니까?");
        int numberOfParticipants = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfParticipants; i++) {
            System.out.println("참여자 이름을 입력하세요:");
            String participantName = scanner.nextLine();
            Participant participant = new Participant(participantName);

            boolean added = false;
            while (!added) {
                System.out.println("참여할 방 번호를 입력하세요:");
                clubMatcher.printClubs();
                int selectedClubNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Club selectedClub = clubMatcher.getClubByNumber(selectedClubNumber);
                if (selectedClub != null && selectedClub.addParticipant(participant)) {
                    System.out.println(participantName + " 님이 방 번호 " + selectedClubNumber + "에 참여하였습니다.");
                    clubMatcher.printClubDetails(selectedClubNumber);
                    added = true;
                } else {
                    System.out.println("참여 실패: 방 번호가 잘못되었거나 방이 가득 찼습니다. 다시 시도해주세요.");
                }
            }
        }
    }
}

