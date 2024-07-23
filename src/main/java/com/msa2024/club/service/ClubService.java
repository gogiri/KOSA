package com.msa2024.club.service;

import java.util.Scanner;

public interface ClubService {
	// 1. 전체 출력
	void printClub();
	// 2. 등록
	void makingClub(String userEmail, Scanner scanner);
	// 3. 삭제
	void deleteClub(String userEmail, Scanner scanner);
	// 4. 참가
	void addClub(String userEmail, Scanner scanner);
	// 5. 참여중인 소모임
	void printMyClub(String userEmail);
}
