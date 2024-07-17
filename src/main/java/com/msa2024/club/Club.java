package com.msa2024.club;

import java.util.List;

public class Club {
	private int clubNumber;					// 방 번호
	private Host host;						// 1.방장
	private String menuType;				// 2.메뉴종류
	private String matchName;				// 3.방 이름
	private int matchMaxSize;				// 4.방 최대 인원
	private String matchDate;				// 5.약속 날짜
	private String matchTime;				// 6.약속 시간
	private String matchPlace;				// 7.약속 장소
	private boolean isDelivery = false;		// 8.배달 여부
	private List<Participant> participants;
	

	public Club(Host host, String menuType, String matchName, int matchMaxSize, String matchDate, String matchTime, String matchPlace, boolean isDelivery) {
		this.host = host;					// 1.방장
		this.menuType = menuType;			// 2.메뉴종류
		this.matchName = matchName;			// 3.방 이름
		this.matchMaxSize = matchMaxSize;	// 4.방 최대 인원
		this.matchDate = matchDate;			// 5.약속 날짜
		this.matchTime = matchTime;			// 6.약속 시간
		this.matchPlace = matchPlace;		// 7.약속 장소
		this.isDelivery = isDelivery;		// 8.배달 여부
	}


	public Club(int nextClubNumber,Host host, String menuType, String matchName, int matchMaxSize, String matchDate, String matchTime, String matchPlace, boolean isDelivery) {
		this.clubNumber = nextClubNumber;	// 0.방번호
		this.host = host;					// 1.방장
		this.menuType = menuType;			// 2.메뉴종류
		this.matchName = matchName;			// 3.방 이름
		this.matchMaxSize = matchMaxSize;	// 4.방 최대 인원
		this.matchDate = matchDate;			// 5.약속 날짜
		this.matchTime = matchTime;			// 6.약속 시간
		this.matchPlace = matchPlace;		// 7.약속 장소
		this.isDelivery = isDelivery;		// 8.배달 여부
	}



	public boolean addParticipant(Participant participant) {
    	// 참가자 사이즈가 방 최대 인원보다 낮으면 참가
        if (participants.size() < matchMaxSize) {
            participants.add(participant);
            return true;
        } else {
            return false;
        }
    }


	public int getClubNumber() {
		return clubNumber;
	}


	public void setClubNumber(int clubNumber) {
		this.clubNumber = clubNumber;
	}


	public Host getHost() {
		return host;
	}


	public void setHost(Host host) {
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


	public boolean getIsDelivery() {
		return isDelivery;
	}


	public void setDelivery(boolean isDelivery) {
		this.isDelivery = isDelivery;
	}


	public List<Participant> getParticipants() {
		return participants;
	}


	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
	
	
}
