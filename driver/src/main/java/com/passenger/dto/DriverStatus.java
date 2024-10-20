package com.passenger.dto;

import java.time.LocalDateTime;

public class DriverStatus {
    private String driverId;
    private String status; // "available", "in-transit", "offline"
    private double latitude;
    private double longitude;
    private String vehicleType;
    private LocalDateTime timestamp;

    public DriverStatus(){}

    public DriverStatus(String driverId, String status, double latitude, double longitude, String vehicleType, LocalDateTime timestamp) {
        this.driverId = driverId;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicleType = vehicleType;
        this.timestamp = timestamp;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
