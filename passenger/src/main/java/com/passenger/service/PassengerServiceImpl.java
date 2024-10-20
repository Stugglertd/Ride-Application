package com.passenger.service;

import com.passenger.entity.Booking;
import com.passenger.repo.BookingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.passenger.entity.Passenger;
import com.passenger.repo.PassengerRepo;

@Service
public class PassengerServiceImpl implements PassengerService{
	private final PassengerRepo passengerRepo;
	private final BookingRepo bookingRepo;
	private final KafkaTemplate<String,String> kafkaTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(PassengerServiceImpl.class);

	private static final String Book_RIDE_TOPIC = "bookRideTopic";

	public PassengerServiceImpl(PassengerRepo passengerRepo, BookingRepo bookingRepo, KafkaTemplate<String, String> kafkaTemplate) {
		this.passengerRepo = passengerRepo;
        this.bookingRepo = bookingRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	public Passenger registerPassenger(Passenger passenger) {
		Passenger savedPassenger = passengerRepo.save(passenger);
		return savedPassenger;
	}

	@Override
	public Booking bookRide(String passengerId, String location) {
		Booking booking = new Booking(passengerId,"PENDING");
		Booking savedBooking = bookingRepo.save(booking);
//		will send this to book cab. (location string as param)
		kafkaTemplate.send(Book_RIDE_TOPIC,savedBooking.getBookingId().toString()+ " " + location);
		return savedBooking;
	}

	@KafkaListener(topics = "ride_confirm",groupId = "myGroup")
	public void consumeRideConfirmation(String consumedMsg){
		final String[] s = consumedMsg.split(" ");
		String status = s[0];
		String bookingId = s[1];
		if(status.equals("Success")){
			final Booking booking = bookingRepo.findById(Integer.valueOf(bookingId)).get();
			booking.setBookingStatus("Booked");
			final Booking savedBooking = bookingRepo.save(booking);

			LOGGER.info("Saved Booking : "+ savedBooking);
		}
		else{
			final Booking booking = bookingRepo.findById(Integer.valueOf(bookingId)).get();
			booking.setBookingStatus("Failed");

			final Booking savedBooking = bookingRepo.save(booking);

			LOGGER.info("Saved Booking : "+ savedBooking);
		}
	}
}
