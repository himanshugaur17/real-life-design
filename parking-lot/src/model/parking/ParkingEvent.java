package model.parking;

import model.Vehicle;

public class ParkingEvent {
    private final ParkingEventType parkingEventType;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;

    public ParkingEvent(ParkingEventType parkingEventType, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.parkingEventType = parkingEventType;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public ParkingEventType getParkingEventType() {
        return parkingEventType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

}
