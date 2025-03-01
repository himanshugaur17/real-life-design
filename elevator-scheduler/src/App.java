import java.util.Arrays;
import java.util.List;

import elevator.Elevator;
import elevator.request.OutsideRequest;
import elevator.request.serving.strategy.NearestFloorPickerStrategy;
import elevator.state.Direction;
import elevator.state.ElevatorRequest;

public class App {
    public static void main(String[] args) throws Exception {
        // here floor strategy is non-singleton
        Elevator elevator = new Elevator("elevator-1", new NearestFloorPickerStrategy());
        List<ElevatorRequest> reqs = Arrays.asList(new OutsideRequest(8, Direction.UP),
                new OutsideRequest(15, Direction.UP), new OutsideRequest(2, Direction.UP),
                new OutsideRequest(100, Direction.UP),
                new OutsideRequest(12, Direction.UP), new OutsideRequest(5, Direction.UP),
                new OutsideRequest(12, Direction.UP), new OutsideRequest(1, Direction.UP),
                new OutsideRequest(7, Direction.UP));
        int count = 0;
        for (ElevatorRequest eR : reqs) {
            elevator.request(eR);
            count++;
            if (count == 5)
                elevator.start();
        }
    }
}
/*
 * Outside request
 * ->elevator-> press direction to head
 * Inside request
 * ->elevator-> floor where you want to go
 * 
 * Routing of Outside requests (make it strategy)
 * Outside Request comes in the direction of lift movement-->
 * First fulfill all in same direction
 * Outside request comes in direction opposite to lift movement-->
 * 
 */
