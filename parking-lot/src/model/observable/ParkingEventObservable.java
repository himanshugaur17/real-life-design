package model.observable;

import model.parking.ParkingEvent;
import observers.Observer;

public interface ParkingEventObservable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(ParkingEvent parkingEvent);
}
