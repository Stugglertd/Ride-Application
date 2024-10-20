package com.driver.controller;

import com.driver.entity.Driver;
import com.driver.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/register")
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver){
        final Driver savedDriver = driverService.registerDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Driver> updateDriver(@RequestParam Integer driverId,
                                               @RequestParam Boolean isAvailable,
                                               @RequestParam String location){
        Driver updatedDriver = driverService.updateDriverDetails(driverId,isAvailable,location);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllAvailableDrivers(){
        final List<Driver> allAvailableDrivers = driverService.getAllAvailableDrivers();
        return new ResponseEntity<>(allAvailableDrivers,HttpStatus.OK);
    }
}
