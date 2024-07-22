package com.msa2024.club.service;

public interface ClubService {
	// 1. 전체 출력
	void printClub();
	// 2. 등록
	void makingClub(String userEmail);
	// 3. 삭제
	void deleteClub(String userEmail);
	// 4. 참가
	void addClub(String userEmail);
	// 5. 참여중인 소모임
	void printMyClub(String userEmail);
}
