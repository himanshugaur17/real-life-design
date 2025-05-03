package loyalty.program.points.rule.engine;

import java.util.List;

import loyalty.program.models.Order;
import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;

public interface PointAllocationRuleEngine {
    List<Point> allocatePoints(Order order, PointsWallet pointsWallet);
}
