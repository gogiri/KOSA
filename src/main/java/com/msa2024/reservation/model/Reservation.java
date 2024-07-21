package com.msa2024.reservation.model;



public class Reservation {
    private int roomSeq;
    private String email;
    private String telePhone;
    private String reservationDate;
    private String startTime;

    public Reservation() {
    }

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
}