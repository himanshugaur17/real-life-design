package loyalty.program.points.rule.engine;

import java.util.ArrayList;
import java.util.List;

import loyalty.program.models.Order;
import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;
import loyalty.program.points.rule.PointAllocationRule;

public class StdPointAllocationRuleEngineImpl implements PointAllocationRuleEngine {
    private final List<PointAllocationRule> pointAllocationRules;

    public StdPointAllocationRuleEngineImpl(List<PointAllocationRule> pointAllocationRules) {
        this.pointAllocationRules = pointAllocationRules;
    }

    @Override
    public List<Point> allocatePoints(Order order, PointsWallet pointsWallet) {
        List<Point> points = new ArrayList<>();
        for (PointAllocationRule pointAllocationRule : pointAllocationRules) {
            points.addAll(pointAllocationRule.allocatePoints(order, pointsWallet));
        }
        return points;
    }

}
