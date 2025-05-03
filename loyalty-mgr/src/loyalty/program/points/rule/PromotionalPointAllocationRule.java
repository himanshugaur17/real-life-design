package loyalty.program.points.rule;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import loyalty.program.models.Order;
import loyalty.program.models.OrderItem;
import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;

public class PromotionalPointAllocationRule implements PointAllocationRule {
    private final int promotionalPointMultiplier;

    public PromotionalPointAllocationRule(int promotionalPointMultiplier) {
        this.promotionalPointMultiplier = promotionalPointMultiplier;
    }

    @Override
    public List<Point> allocatePoints(Order order, PointsWallet pointsWallet) {
        return order.items().stream().filter(OrderItem::isPromotional).map(item -> {
            return new Point("pt" + new Random().nextLong(),
                    (long) (item.getItemValue() * promotionalPointMultiplier),
                    promotionalPointMultiplier, LocalDate.now().plusDays(30));
        }).collect(Collectors.toList());
    }
}
