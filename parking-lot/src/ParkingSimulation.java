import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import exceptions.SpotNotFoundException;
import model.Vehicle;
import model.gate.EntryGate;
import model.gate.ExitGate;
import model.parking.ParkingLot;
import model.parking.ParkingSpot;
import model.parking.ParkingSpotType;
import model.parking.ParkingTicket;
import service.ParkingService;
import service.strategy.NearestFreeSpotFinderStrategy;
import service.strategy.ParkingSpotFinderStrategy;

public class ParkingSimulation {
    public static void main(String[] args) throws SpotNotFoundException {
        // Creating singleton classes
        ParkingLot parkingLot = new ParkingLot();
        ParkingSpotFinderStrategy parkingSpotFinderStrategy = new NearestFreeSpotFinderStrategy();
        ParkingService parkingService = new ParkingService(parkingSpotFinderStrategy);
        parkingService.addObserver(parkingSpotFinderStrategy);
        parkingLot.registerObserver(parkingSpotFinderStrategy);

        // Creating Parking Spots
        ParkingSpot spot1 = new ParkingSpot("A1", new AtomicBoolean(true), "Compact Spot", ParkingSpotType.MINI);
        ParkingSpot spot11 = new ParkingSpot("A11", new AtomicBoolean(true), "Compact Spot", ParkingSpotType.MINI);
        ParkingSpot spot2 = new ParkingSpot("A2", new AtomicBoolean(true), "Large Spot", ParkingSpotType.LARGE);

        // Adding Parking Spots to Parking Lot
        parkingLot.addParkingSpot(spot1);
        parkingLot.addParkingSpot(spot11);
        parkingLot.addParkingSpot(spot2);

        // Creating Gates
        EntryGate entryGate = new EntryGate("EG1", "John", parkingService);
        ExitGate exitGate = new ExitGate("XG1", "Alice", parkingService);

        // Adding gates to the parking lot (Assuming gates are independent of ParkingLot
        // for this example)
        parkingLot.addEntryGate(entryGate);
        parkingLot.addExitGate(exitGate);

        // Creating Vehicle

        // Simulate Vehicle Entry
        List<CompletableFuture<Void>> cfs = new ArrayList();
        for (int i = 0; i < 3; i++) {
            int j = i;
            cfs.add(CompletableFuture.runAsync(() -> {
                Vehicle vehicle = new Vehicle("ABC" + "(" + j + ")", ParkingSpotType.MINI);
                try {
                    simulateParkingAndExit(entryGate, exitGate, vehicle);
                } catch (SpotNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }, Executors.newFixedThreadPool(1)));

        }

    }

    private static void simulateParkingAndExit(EntryGate entryGate, ExitGate exitGate, Vehicle vehicle)
            throws SpotNotFoundException, InterruptedException {
        ParkingTicket parkingTicket = entryGate.processVehicleEntry(vehicle);
        System.out.println("Vehicle entered with Ticket ID: " + parkingTicket.getId() + " for Parking Spot: "
                + parkingTicket.getParkingSpot().getId() + " by thread: " + Thread.currentThread().getId());
        Thread.sleep(new Random().nextInt(1000) * 10);
        // Simulate Vehicle Exit
        exitGate.processVehicleExit(parkingTicket);
        System.out.println("Vehicle with Ticket ID: " + parkingTicket.getId() + " has exited the parking lot.");
    }
}
