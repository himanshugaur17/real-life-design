package loyalty.program.tier.qualification.strategy.impl;

import loyalty.program.models.Customer;
import loyalty.program.models.TierCalculationData;
import loyalty.program.tier.qualification.strategy.TierQualificationStrategy;

public class PointBasedTierQualificationStrategy implements TierQualificationStrategy {
    private final long pointsThreshold;

    public PointBasedTierQualificationStrategy(long pointsThreshold) {
        this.pointsThreshold = pointsThreshold;
    }

    @Override
    public boolean isQualified(Customer customer, TierCalculationData tierCalculationData) {
        // TODO Auto-generated method stub
        return tierCalculationData.getPointsEarnedIn12Months(customer) >= pointsThreshold;
    }

}
