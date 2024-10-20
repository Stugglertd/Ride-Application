package com.passenger.service;

import com.passenger.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver registerDriver(Driver driver);
    Driver updateDriverDetails(Integer driverId,Boolean isAvailable,String location);
    List<Driver> getAllAvailableDrivers();
}
