package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.SpotNotFoundException;
import model.Vehicle;
import model.observable.ParkingEventObservable;
import model.parking.ParkingEvent;
import model.parking.ParkingEventType;
import model.parking.ParkingSpot;
import model.parking.ParkingTicket;
import observers.Observer;
import service.strategy.ParkingSpotFinderStrategy;

public class ParkingService implements ParkingEventObservable {
    private final ParkingSpotFinderStrategy parkingSpotFinderStrategy;
    private final List<Observer> parkingEventObservers = new ArrayList<>();

    public ParkingService(ParkingSpotFinderStrategy parkingSpotFinderStrategy) {
        this.parkingSpotFinderStrategy = parkingSpotFinderStrategy;
    }

    public ParkingTicket entry(Vehicle vehicle) throws SpotNotFoundException {
        ParkingSpot parkingSpot = parkingSpotFinderStrategy.findParkingSpot(vehicle.getSupportedParkingSpotType());
        parkingSpot.occupy();
        ParkingTicket parkingTicket = new ParkingTicket(vehicle, parkingSpot);
        notifyObservers(new ParkingEvent(ParkingEventType.ENTRY, vehicle, parkingSpot));
        return parkingTicket;

    }

    public void exit(ParkingTicket parkingTicket) {
        parkingTicket.setExitTime(LocalDateTime.now());
        System.out.println("parking ticket pric: " + parkingTicket.calculatePrice());
        Vehicle associatedVehicle = parkingTicket.getVehicle();
        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        notifyObservers(new ParkingEvent(ParkingEventType.EXIT, associatedVehicle, parkingSpot));
        parkingSpot.vacate();

    }

    @Override
    public void addObserver(Observer observer) {
        parkingEventObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        parkingEventObservers.remove(observer);
    }

    @Override
    public void notifyObservers(ParkingEvent parkingEvent) {
        parkingEventObservers
                .forEach(
                        pEObs -> pEObs.update(parkingEvent));
    }
}
