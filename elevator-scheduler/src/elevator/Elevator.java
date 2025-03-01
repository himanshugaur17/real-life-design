package elevator;

import java.util.Optional;
import java.util.Random;

import elevator.request.serving.strategy.ElevatorNextFloorPickerStrategy;
import elevator.state.Direction;
import elevator.state.ElevatorRequest;
import elevator.state.ElevatorState;

public class Elevator extends Thread {
    String id;
    int maxCapacity;
    ElevatorState elevatorState;
    ElevatorNextFloorPickerStrategy nextFloorStrategy;

    public Elevator(String id, ElevatorNextFloorPickerStrategy nextFloorStrategy) {
        super();
        this.id = id;
        this.elevatorState = new ElevatorState(Direction.UP, 5);
        this.nextFloorStrategy = nextFloorStrategy;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void request(ElevatorRequest request) {
        elevatorState.add(request);
    }

    @Override
    public void run() {
        while (true) {
            Optional<ElevatorRequest> nextRequestToServedOptional = nextFloorStrategy.nextFloor(elevatorState);
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (nextRequestToServedOptional.isEmpty()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            ElevatorRequest nextRequestToServed = nextRequestToServedOptional.get();
            int nextFloor = nextRequestToServed.getFloor();
            if ((nextFloor > elevatorState.getCurrFloor() && elevatorState.getCurrDir().equals(Direction.DOWN))
                    || (elevatorState.getCurrFloor() < nextFloor && elevatorState.getCurrDir().equals(Direction.UP)))
                elevatorState.flipDirection();
            elevatorState.setCurrFloor(nextFloor);
            elevatorState.updateProccessedRequest(nextRequestToServed);
        }
    }

}
