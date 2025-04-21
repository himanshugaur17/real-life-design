package loyalty.program.rules.impl;

import java.util.Collections;
import java.util.List;

import loyalty.program.models.CustomerAction;
import loyalty.program.models.LoyaltyPoint;
import loyalty.program.models.PointType;
import loyalty.program.rules.LoyaltyPointRule;

public class SpendingThresholdStandardLoyaltyPointRule implements LoyaltyPointRule {
    private final double spendingThreshold;

    public SpendingThresholdStandardLoyaltyPointRule(double spendingThreshold) {
        this.spendingThreshold = spendingThreshold;
    }

    @Override
    public List<LoyaltyPoint> applyRule(CustomerAction context) {
        double amountSpent = context.amountSpent();
        if (amountSpent < spendingThreshold)
            return Collections.emptyList();
        return List.of(new LoyaltyPoint(PointType.STANDARD, (int) Math.round(amountSpent - spendingThreshold) + 10));
    }

}
