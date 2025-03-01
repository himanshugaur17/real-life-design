package elevator.request;

import elevator.request.serving.strategy.RequestStatus;
import elevator.state.Direction;
import elevator.state.ElevatorRequest;

public class OutsideRequest extends ElevatorRequest {
    int pressingFloor;
    Direction direction;

    public OutsideRequest(int pressingFloor, Direction direction) {
        this.pressingFloor = pressingFloor;
        this.direction = direction;
        this.setStatus(RequestStatus.NEW);
    }

    @Override
    public int getFloor() {
        return pressingFloor;
    }

}
