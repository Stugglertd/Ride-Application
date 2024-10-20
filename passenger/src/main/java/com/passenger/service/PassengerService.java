package com.passenger.service;

import com.passenger.entity.Booking;
import com.passenger.entity.Passenger;

public interface PassengerService {
	Passenger registerPassenger(Passenger passenger);
	Booking bookRide(String passengerId, String location);
}
