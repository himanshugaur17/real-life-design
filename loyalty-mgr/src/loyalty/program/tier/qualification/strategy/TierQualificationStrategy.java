package loyalty.program.tier.qualification.strategy;

import loyalty.program.models.Customer;
import loyalty.program.models.TierCalculationData;

public interface TierQualificationStrategy {
    boolean isQualified(Customer customer, TierCalculationData tierCalculationData);
}
