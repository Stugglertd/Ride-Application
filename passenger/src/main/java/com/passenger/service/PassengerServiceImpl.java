package com.passenger.service;

import com.passenger.entity.Booking;
import com.passenger.repo.BookingRepo;
import org.springframework.stereotype.Service;

import com.passenger.entity.Passenger;
import com.passenger.repo.PassengerRepo;

@Service
public class PassengerServiceImpl implements PassengerService{
	private final PassengerRepo passengerRepo;
	private final BookingRepo bookingRepo;

	public PassengerServiceImpl(PassengerRepo passengerRepo, BookingRepo bookingRepo) {
		this.passengerRepo = passengerRepo;
        this.bookingRepo = bookingRepo;
    }

	@Override
	public Passenger registerPassenger(Passenger passenger) {
		Passenger savedPassenger = passengerRepo.save(passenger);
		return savedPassenger;
	}

	@Override
	public Booking bookRide(String passengerId, String location) {
		Booking booking = new Booking(passengerId,"PENDING");

//		will send this to book cab. (location string as param)

		return bookingRepo.save(booking);
	}


}
