package loyalty.program.models;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PointsWallet {
    private final String id;
    private final String customerId;
    private final List<Point> points;
    private final AtomicLong pointsBalance;

    public PointsWallet(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
        this.points = new CopyOnWriteArrayList<>();
        this.pointsBalance = new AtomicLong(0);
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public void addPoint(List<Point> points) {
        this.points.addAll(points);
        this.pointsBalance.addAndGet(points.stream().mapToLong(Point::redeem).sum());
    }

    public void removePoints(List<Point> points) {
        this.points.removeAll(points);
    }

    public long getPointsBalance() {
        return pointsBalance.get();
    }

    public boolean redeemPoints(long expectedPoints, int pointsToRedeem) {
        return this.pointsBalance.compareAndSet(expectedPoints, expectedPoints - pointsToRedeem);
    }

}
