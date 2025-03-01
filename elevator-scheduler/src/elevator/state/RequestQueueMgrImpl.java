package elevator.state;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import elevator.request.serving.strategy.RequestStatus;

public class RequestQueueMgrImpl implements RequestQueueManager {
    LinkedList<ElevatorRequest> unprocessedRequests = new LinkedList<>();
    Set<ElevatorRequest> lastNProcessedRequests = new LinkedHashSet<>(10);

    @Override
    public List<ElevatorRequest> getUnprocessedRequests() {
        return unprocessedRequests.stream().filter(eR -> eR.getStatus() != RequestStatus.IN_QUEUE)
                .collect(Collectors.toList());
    }

    @Override
    public void updateProccessedRequest(ElevatorRequest nextRequestToServed) {
        unprocessedRequests.remove(nextRequestToServed);
        lastNProcessedRequests.add(nextRequestToServed);
    }

    @Override
    public void add(ElevatorRequest request) {
        unprocessedRequests.add(request);
    }
}
