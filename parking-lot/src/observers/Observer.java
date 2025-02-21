package observers;

import model.parking.ParkingEvent;

@FunctionalInterface
public interface Observer {
    void update(ParkingEvent parkingEvent);
}
