package com.msa2024.reservation.model;

public class Reservation {
    private int roomSeq; // 회의실 넘버
    private String email; // 이메일
    private String telePhone; // 핸드폰번호
    private String reservationDate; // 예약 날짜
    private String startTime; // 사용 시작 시간

    public Reservation() {}
    
    public Reservation(int roomSeq, String email, String telePhone, String reservationDate, String startTime) {
    	this.roomSeq = roomSeq;
        this.email = email;
        this.telePhone = telePhone;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
	}

	public int getRoomSeq() {
        return roomSeq;
    }

    public void setRoomSeq(int roomSeq) {
        this.roomSeq = roomSeq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String getTelePhone() {
    	return telePhone;
    }
    
    public void setTelePhone(String telePhone) {
    	this.telePhone = telePhone;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

	@Override
	public String toString() {
		return "Reservation [roomSeq=" + roomSeq + ", email=" + email + ", telePhone=" + telePhone
				+ ", reservationDate=" + reservationDate + ", startTime=" + startTime + "]";
	}

	public static Reservation fromString(String line) {
        String[] parts = line.split(",");
        return new Reservation(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4]
        );
    }
}
