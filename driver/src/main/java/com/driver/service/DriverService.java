package com.driver.service;

import com.driver.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver registerDriver(Driver driver);
    Driver updateDriverDetails(Integer driverId,Boolean isAvailable,String location);
    List<Driver> getAllAvailableDrivers();
}
