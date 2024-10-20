package com.passenger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.passenger.entity.Passenger;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger, String>{

}
