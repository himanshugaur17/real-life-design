package service.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import exceptions.SpotNotFoundException;
import model.parking.ParkingEvent;
import model.parking.ParkingEventType;
import model.parking.ParkingSpot;
import model.parking.ParkingSpotType;

public class NearestFreeSpotFinderStrategy implements ParkingSpotFinderStrategy {
    private final Map<ParkingSpotType, PriorityBlockingQueue<ParkingSpot>> freeParkingSpots = new HashMap<>();

    public NearestFreeSpotFinderStrategy() {
        for (ParkingSpotType parkingSpotType : ParkingSpotType.values())
            freeParkingSpots.put(parkingSpotType,
                    new PriorityBlockingQueue<>(1, (ps1, ps2) -> ps1.getId().compareTo(ps2.getId())));
    }

    @Override
    public void update(ParkingEvent parkingEvent) {
        ParkingEventType parkingEventType = parkingEvent.getParkingEventType();
        ParkingSpot parkingSpot = parkingEvent.getParkingSpot();
        if (parkingEventType.equals(ParkingEventType.EXIT)) {
            PriorityBlockingQueue<ParkingSpot> freeParkingSpotsQueue = freeParkingSpots
                    .get(parkingSpot.getParkingSpotType());
            freeParkingSpotsQueue.add(parkingSpot);
        }
    }

    @Override
    public ParkingSpot findParkingSpot(ParkingSpotType supportedParkingSpotType) throws SpotNotFoundException {
        PriorityBlockingQueue<ParkingSpot> freeParkingSpotsQueue = freeParkingSpots
                .get(supportedParkingSpotType);
        try {
            ParkingSpot parkingSpot = freeParkingSpotsQueue.poll(2, TimeUnit.SECONDS);
            if (Objects.isNull(parkingSpot))
                throw new SpotNotFoundException("spot not found");
            return parkingSpot;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        throw new SpotNotFoundException("something went wroing while finding spot");
    }

    @Override
    public void onNewParketSpotAddition(ParkingSpot parkingSpot) {
        PriorityBlockingQueue<ParkingSpot> freeParkingSpotsQueue = freeParkingSpots
                .get(parkingSpot.getParkingSpotType());
        freeParkingSpotsQueue.add(parkingSpot);
    }

}
