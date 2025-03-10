package model.gate;

import java.util.concurrent.locks.ReentrantLock;

import service.ParkingService;

public abstract class Gate {
    protected final String gateId;
    protected String gateAttendant;
    protected final ParkingService parkingService;
    protected final ReentrantLock reentrantLock = new ReentrantLock();

    public Gate(String gateId, String gateAttendant, ParkingService parkingService) {
        this.gateId = gateId;
        this.gateAttendant = gateAttendant;
        this.parkingService = parkingService;
    }

    public String getGateId() {
        return gateId;
    }

    public String getGateAttendant() {
        return gateAttendant;
    }

    public void setGateAttendant(String gateAttendant) {
        this.gateAttendant = gateAttendant;
    }

}
