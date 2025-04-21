package loyalty.program.service;

import java.util.HashMap;
import java.util.Map;

import loyalty.program.models.Customer;
import loyalty.program.models.LoyaltyTier;

public class TierMgmtServiceImpl implements TierManagementService {
    Map<Customer, LoyaltyTier> loyaltyTierMap = new HashMap<>();

    @Override
    public void evaluateTierForCustomer(Customer customer) {

    }

    @Override
    public LoyaltyTier getCurrentTier(Customer customer) {
        return loyaltyTierMap.get(customer);
    }

}
