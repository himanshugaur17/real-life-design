package loyalty.program.points.rule;

import java.util.List;

import loyalty.program.models.Order;
import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;

public interface PointAllocationRule {
    List<Point> allocatePoints(Order order, PointsWallet pointsWallet);
}
