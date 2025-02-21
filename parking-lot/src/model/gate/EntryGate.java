package model.gate;

import java.util.concurrent.TimeUnit;

import exceptions.SpotNotFoundException;
import model.Vehicle;
import model.parking.ParkingTicket;
import service.ParkingService;

public class EntryGate extends Gate {

    public EntryGate(String gateId, String gateAttendant, ParkingService parkingService) {
        super(gateId, gateAttendant, parkingService);
    }

    public ParkingTicket processVehicleEntry(Vehicle vehicle) throws SpotNotFoundException {
        try {
            if (reentrantLock.tryLock(2, TimeUnit.SECONDS)) {
                return parkingService.entry(vehicle);
            } else {
                System.out.println("try some other gate");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return null;
    }
}
