package com.driver.service;

import com.driver.entity.Driver;
import com.driver.repo.DriverRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements  DriverService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
    private static final String Ride_Confirm_Topic = "ride_confirm";
    private DriverRepo driverRepo;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public DriverServiceImpl(DriverRepo driverRepo, KafkaTemplate<String, String> kafkaTemplate) {
        this.driverRepo = driverRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Driver registerDriver(Driver driver) {
        return driverRepo.save(driver);
    }

    @Override
    public Driver updateDriverDetails(Integer driverId, Boolean isAvailable, String location) {
        final Optional<Driver> optionalDriver = driverRepo.findById(driverId);
        if(optionalDriver.isPresent()){
            final Driver driver = optionalDriver.get();
            driver.setAvailable(isAvailable);
            driver.setLocation(location);

            return driverRepo.save(driver);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Driver> getAllAvailableDrivers() {
        final List<Driver> drivers = driverRepo.findAll();
        return drivers.stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }

    //Consumer to consume booking request
    //will check all driver for any matched location
    @KafkaListener(topics = "bookRideTopic",groupId = "myGroup")
    public void consumeRideRequest(String msg){
        final String[] s = msg.split(" ");
        String location = s[1];
        String bookingId = s[0];
        LOGGER.info("Location consumed by consumer " + location);

        final Optional<Driver> driver = driverRepo.findAll().stream()
                .filter(d -> d.getLocation().equals(location) && d.isAvailable())
                .findFirst();

        if(driver.isPresent()){
            kafkaTemplate.send(Ride_Confirm_Topic,"Success "+bookingId);
        }
        else{
            kafkaTemplate.send(Ride_Confirm_Topic,"Failed "+bookingId);
        }
    }


}
