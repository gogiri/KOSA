package com.msa2024.club;

public class Participant {
    private String name;

    public Participant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}