package service.strategy;

import exceptions.SpotNotFoundException;
import model.parking.ParkingSpot;
import model.parking.ParkingSpotType;
import observers.Observer;
import observers.ParkingLotObserver;

public interface ParkingSpotFinderStrategy extends Observer, ParkingLotObserver {
    ParkingSpot findParkingSpot(ParkingSpotType supportedParkingSpotType) throws SpotNotFoundException;
}
