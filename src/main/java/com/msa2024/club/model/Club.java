package com.msa2024.club.model;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa2024.user.model.User;

public class Club{

	@JsonProperty("clubNumber")
	private int clubNumber;			//1. 방 번호

	@JsonProperty("email")
	private String email;			//2. 이메일

	@JsonProperty("menuType")
	private String menuType;		//3. 메뉴 종류

	@JsonProperty("matchName")
	private String matchName;		//4. 방 제목

	@JsonProperty("matchMaxSize")
	private int matchMaxSize;		//5. 최대 인원

	@JsonProperty("matchDate")
	private String matchDate;		//6. 소모임 날짜

	@JsonProperty("matchTime")
	private String matchTime;		//7. 소모임 시간

	@JsonProperty("matchPlace")
	private String matchPlace;		//8. 소모임 장소

//	@JsonProperty("isDelivery")
	private String isDelivery;		//9. 배달 여부

	public Club(int clubNumber, String userEmail, String menuType, String matchName, int matchMaxSize, String matchDate, String matchTime, String matchPlace,
			String isDelivery) {
		this.email = userEmail;
		this.clubNumber = clubNumber;
		this.menuType = menuType;
		this.matchName = matchName;
		this.matchMaxSize = matchMaxSize;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.matchPlace = matchPlace;
		this.isDelivery = isDelivery;
	}
	public Club() {
		
	}

	public int getClubNumber() {
		return clubNumber;
	}

	public void setClubNumber(int clubNumber) {
		this.clubNumber = clubNumber;
	}


	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public int getMatchMaxSize() {
		return matchMaxSize;
	}

	public void setMatchMaxSize(int matchMaxSize) {
		this.matchMaxSize = matchMaxSize;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getMatchPlace() {
		return matchPlace;
	}

	public void setMatchPlace(String matchPlace) {
		this.matchPlace = matchPlace;
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
