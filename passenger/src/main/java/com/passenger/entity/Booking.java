package com.passenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Booking {
    @Id
    Integer bookingId;
    String passengerId;
    String bookingStatus;//"BOOKED","PENDING"

    public Booking(){}

    public Booking(String passengerId, String bookingStatus) {
        this.passengerId = passengerId;
        this.bookingStatus = bookingStatus;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
