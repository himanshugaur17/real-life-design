package model.gate;

import model.parking.ParkingTicket;
import service.ParkingService;

public class ExitGate extends Gate {

    public ExitGate(String gateId, String gateAttendant, ParkingService parkingService) {
        super(gateId, gateAttendant, parkingService);
        // TODO Auto-generated constructor stub
    }

    public void processVehicleExit(ParkingTicket parkingTicket) {
        parkingService.exit(parkingTicket);
    }

}
