package elevator.state;

import java.util.List;

public interface RequestQueueManager {
    List<ElevatorRequest> getUnprocessedRequests();

    void updateProccessedRequest(ElevatorRequest nextRequestToServed);

    void add(ElevatorRequest request);
}
