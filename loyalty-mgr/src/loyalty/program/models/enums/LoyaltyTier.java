package loyalty.program.models.enums;

import loyalty.program.tier.qualification.strategy.TierQualificationStrategy;
import loyalty.program.tier.qualification.strategy.impl.PointAndOrderBasedTierQualificationStrategy;
import loyalty.program.tier.qualification.strategy.impl.PointBasedTierQualificationStrategy;

public enum LoyaltyTier {
    BRONZE(0, new PointBasedTierQualificationStrategy(0)),
    SILVER(1000, new PointAndOrderBasedTierQualificationStrategy(1000, 5)),
    GOLD(5000, new PointAndOrderBasedTierQualificationStrategy(5000, 10)),
    PLATINUM(10000, new PointBasedTierQualificationStrategy(10000));

    private final int minPoints;
    private final TierQualificationStrategy qualificationStrategy;

    LoyaltyTier(int minPoints, TierQualificationStrategy qualificationStrategy) {
        this.minPoints = minPoints;
        this.qualificationStrategy = qualificationStrategy;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public TierQualificationStrategy getQualificationStrategy() {
        return qualificationStrategy;
    }

}
