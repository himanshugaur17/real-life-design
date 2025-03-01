package elevator.request;

import elevator.state.ElevatorRequest;

public class InsideRequest extends ElevatorRequest {
    int currFloor;
    int destinationFloor;

    @Override
    public int getFloor() {
        return currFloor;
    }

}
