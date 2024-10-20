package com.passenger.service;

import com.passenger.entity.Driver;
import com.passenger.repo.DriverRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements  DriverService{
    private DriverRepo driverRepo;

    public DriverServiceImpl(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
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
}
