package observers;

import model.parking.ParkingSpot;

public interface ParkingLotObserver {
    void onNewParketSpotAddition(ParkingSpot parkingSpot);
}
