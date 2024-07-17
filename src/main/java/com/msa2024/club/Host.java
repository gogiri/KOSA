package com.msa2024.club;

public class Host {
    private String name;

    public Host(String name) {
        this.name = name;
    }

    public Club createClub(Host host, String menuType, String matchName, 
    						int matchMaxSize, String matchDate, String matchTime, 
    						String matchPlace, boolean isDelivery) {
    	
        return new Club(this, menuType, matchName, 
        				matchMaxSize, matchDate, matchTime, 
        				matchPlace, isDelivery);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
