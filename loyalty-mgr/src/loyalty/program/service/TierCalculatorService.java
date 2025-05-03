package loyalty.program.service;

import loyalty.program.models.Customer;
import loyalty.program.models.enums.LoyaltyTier;

public interface TierCalculatorService {
    LoyaltyTier calculateTier(Customer customer);
}
