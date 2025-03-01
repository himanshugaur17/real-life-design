package elevator.request.serving.strategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;

import elevator.state.ElevatorRequest;
import elevator.state.ElevatorState;

public class NearestFloorPickerStrategy implements ElevatorNextFloorPickerStrategy {

    private final TreeMap<Integer, ElevatorRequest> elevatorRequestsSortedByFloor = new TreeMap<>();

    @Override
    public Optional<ElevatorRequest> nextFloor(ElevatorState elevatorState) {

        List<ElevatorRequest> unprocessedRequests = elevatorState.getUnprocessedRequests();
        unprocessedRequests.forEach(eR -> {
            elevatorRequestsSortedByFloor.put(eR.getFloor(), eR);
            eR.setStatus(RequestStatus.IN_QUEUE);
        });
        Optional<Map.Entry<Integer, ElevatorRequest>> reqPicked = Optional.empty();
        Optional<Map.Entry<Integer, ElevatorRequest>> floorReqLessThanCurrFloor = Optional
                .ofNullable(elevatorRequestsSortedByFloor.floorEntry(elevatorState.getCurrFloor()));

        if (floorReqLessThanCurrFloor.isEmpty()) {
            // find the floor req just greater than elevator's curr floor
            reqPicked = Optional.ofNullable(elevatorRequestsSortedByFloor.ceilingEntry(elevatorState.getCurrFloor()));
        } else {
            reqPicked = floorReqLessThanCurrFloor;
        }
        reqPicked.ifPresent(entry -> elevatorRequestsSortedByFloor.remove(entry.getKey()));
        return Optional.of(reqPicked.get()).map(Entry::getValue);
    }

}
