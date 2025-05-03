package loyalty.program.tier.qualification.strategy.impl;

import loyalty.program.models.Customer;
import loyalty.program.models.TierCalculationData;
import loyalty.program.tier.qualification.strategy.TierQualificationStrategy;

public class PointAndOrderBasedTierQualificationStrategy implements TierQualificationStrategy {
    private final long pointsThreshold;
    private final long ordersThreshold;

    public PointAndOrderBasedTierQualificationStrategy(long pointsThreshold, long ordersThreshold) {
        this.pointsThreshold = pointsThreshold;
        this.ordersThreshold = ordersThreshold;
    }

    @Override
    public boolean isQualified(Customer customer, TierCalculationData tierCalculationData) {
        return tierCalculationData.getPointsEarnedIn12Months(customer) >= pointsThreshold
                && tierCalculationData.getTotalOrdersIn12Months(customer) >= ordersThreshold;
    }
}
