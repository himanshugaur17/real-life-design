package model.gate;

import exceptions.SpotNotFoundException;
import model.Vehicle;
import model.parking.ParkingTicket;
import service.ParkingService;

public class EntryGate extends Gate {

    public EntryGate(String gateId, String gateAttendant, ParkingService parkingService) {
        super(gateId, gateAttendant, parkingService);
    }

    public ParkingTicket processVehicleEntry(Vehicle vehicle) throws SpotNotFoundException {
        return parkingService.entry(vehicle);
    }
}
