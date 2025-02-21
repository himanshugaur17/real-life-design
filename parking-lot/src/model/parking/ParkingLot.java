package model.parking;

import java.util.ArrayList;
import java.util.List;

import model.gate.EntryGate;
import model.gate.ExitGate;
import observers.ParkingLotObserver;

public class ParkingLot {
    private final List<ParkingSpot> parkingSpots = new ArrayList<>();
    private final List<EntryGate> entryGates = new ArrayList<>();
    private final List<ExitGate> exitGates = new ArrayList<>();
    private final List<ParkingLotObserver> parkingLotObservers = new ArrayList<>();

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
        parkingLotObservers.forEach(pLO -> pLO.onNewParketSpotAddition(parkingSpot));
    }

    public void addEntryGate(EntryGate entryGate) {
        entryGates.add(entryGate);
    }

    public void addExitGate(ExitGate exitGate) {
        exitGates.add(exitGate);
    }

    public void registerObserver(ParkingLotObserver parkingSpotFinderStrategy) {
        parkingLotObservers.add(parkingSpotFinderStrategy);
    }

}
