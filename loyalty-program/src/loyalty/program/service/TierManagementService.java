package loyalty.program.service;

import loyalty.program.models.Customer;
import loyalty.program.models.LoyaltyTier;

public interface TierManagementService {
    void evaluateTierForCustomer(Customer customer);

    LoyaltyTier getCurrentTier(Customer customer);
}
