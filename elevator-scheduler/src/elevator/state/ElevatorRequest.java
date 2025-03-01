package elevator.state;

import elevator.request.serving.strategy.RequestStatus;

public abstract class ElevatorRequest {
    public abstract int getFloor();

    private RequestStatus status=RequestStatus.NEW;

    public void setStatus(RequestStatus inQueue) {
        status = inQueue;
    }

    public RequestStatus getStatus() {
        return status;
    }
}
