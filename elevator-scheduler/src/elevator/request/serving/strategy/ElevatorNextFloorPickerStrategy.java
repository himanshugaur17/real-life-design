package elevator.request.serving.strategy;

import java.util.Optional;

import elevator.state.ElevatorRequest;
import elevator.state.ElevatorState;

public interface ElevatorNextFloorPickerStrategy {
    Optional<ElevatorRequest> nextFloor(ElevatorState elevatorState);
}
