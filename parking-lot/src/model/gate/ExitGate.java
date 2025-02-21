package model.gate;

import java.util.concurrent.TimeUnit;

import model.parking.ParkingTicket;
import service.ParkingService;

public class ExitGate extends Gate {

    public ExitGate(String gateId, String gateAttendant, ParkingService parkingService) {
        super(gateId, gateAttendant, parkingService);
    }

    public void processVehicleExit(ParkingTicket parkingTicket) {
        try {
            if (reentrantLock.tryLock(2, TimeUnit.SECONDS)) {
                parkingService.exit(parkingTicket);
            } else {
                System.out.println("try some other gate");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

}
