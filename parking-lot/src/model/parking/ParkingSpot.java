package model.parking;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final String id;
    private final AtomicBoolean isFree;
    private final String desc;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(String id, AtomicBoolean isFree, String desc, ParkingSpotType parkingSpotType) {
        this.id = id;
        this.isFree = isFree;
        this.desc = desc;
        this.parkingSpotType = parkingSpotType;
    }

    public double calculatePrice(int hours) {
        return parkingSpotType.calculatePrice(hours);
    }

    public String getId() {
        return id;
    }

    public AtomicBoolean getIsFree() {
        return isFree;
    }

    public boolean occupy() {
        return isFree.compareAndSet(true, false);
    }

    public void vacate() {
        isFree.set(true);
    }

    public String getDesc() {
        return desc;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }
}
