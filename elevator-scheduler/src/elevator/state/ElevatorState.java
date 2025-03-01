package elevator.state;

import java.util.List;

public class ElevatorState {
    Direction currDir;
    int currFloor;
    private final RequestQueueManager requestQueueManager = new RequestQueueMgrImpl();

    public ElevatorState(Direction currDir, int currFloor) {
        this.currDir = currDir;
        this.currFloor = currFloor;
    }

    public List<ElevatorRequest> getUnprocessedRequests() {
        return requestQueueManager.getUnprocessedRequests();
    }

    public Direction getCurrDir() {
        return currDir;
    }

    public void setCurrDir(Direction currDir) {
        this.currDir = currDir;
    }

    public int getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(int currFloor) {
        System.out.println(String.format("elevator moving from %s to %s", this.currFloor, currFloor));
        this.currFloor = currFloor;
    }

    public void add(ElevatorRequest request) {
        requestQueueManager.add(request);
    }

    public void flipDirection() {
        if (getCurrDir() == Direction.DOWN)
            setCurrDir(Direction.UP);
        else
            setCurrDir(Direction.DOWN);
    }

    public void updateProccessedRequest(ElevatorRequest nextRequestToServed) {
        requestQueueManager.updateProccessedRequest(nextRequestToServed);
    }
}
