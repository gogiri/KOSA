package com.msa2024.club.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Club {

	@JsonProperty("clubNumber")
	private int clubNumber;

	@JsonProperty("host") // user의 이메일로 처리예정
	private boolean host = true;

	@JsonProperty("menuType")
	private String menuType;

	@JsonProperty("matchName")
	private String matchName;

	@JsonProperty("matchMaxSize")
	private int matchMaxSize;

	@JsonProperty("matchDate")
	private String matchDate;

	@JsonProperty("matchTime")
	private String matchTime;

	@JsonProperty("matchPlace")
	private String matchPlace;

//	@JsonProperty("isDelivery")
	private String isDelivery;

	public Club(int clubNumber, String menuType, String matchName, int matchMaxSize, String matchDate, String matchTime, String matchPlace,
			String isDelivery) {
		this.clubNumber = clubNumber;
		this.host = false;
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

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
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
	
}
