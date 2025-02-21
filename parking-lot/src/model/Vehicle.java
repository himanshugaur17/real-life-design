package model;

import model.parking.ParkingSpotType;
import model.parking.ParkingTicket;

public class Vehicle {
    private final String licensePlate;
    private final ParkingSpotType supportedParkingSpotType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licensePlate, ParkingSpotType supportedParkingSpotType) {
        this.licensePlate = licensePlate;
        this.supportedParkingSpotType = supportedParkingSpotType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public ParkingSpotType getSupportedParkingSpotType() {
        return supportedParkingSpotType;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }
}
