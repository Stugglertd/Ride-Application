package com.passenger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.passenger.entity.Passenger;
import com.passenger.service.PassengerService;

@RestController
@RequestMapping("/passenger/")
public class PassengerController {
	private PassengerService passengerService;

	public PassengerController(PassengerService passengerService) {
		this.passengerService = passengerService;
	}
	
	@PostMapping("register")
	public ResponseEntity<Passenger> registerPassenger(@RequestBody Passenger passenger){
		Passenger registerdPassenger = passengerService.registerPassenger(passenger);
		return new ResponseEntity<Passenger>(registerdPassenger,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> bookRide(@RequestParam String passengerId,@RequestParam String location){

	}
}
