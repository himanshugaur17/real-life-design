package loyalty.program.service;

import java.util.Map;

import loyalty.program.models.Customer;
import loyalty.program.models.LoyaltyTier;
import loyalty.program.models.PointType;
import loyalty.program.models.PointsWallet;
import loyalty.program.observer.PointsWalletObserver;

public class PointsWalletObserverImpl implements PointsWalletObserver {

    @Override
    public void notify(PointsWallet pointsWallet, Customer customer) {
        Map<PointType, Integer> balanceMap = pointsWallet.getBalanceMap();
        long onboardingTime = customer.getOnboardingTimeEpoch();
        int totalPoints = balanceMap.values().stream().mapToInt(x -> x).sum();
        if (System.currentTimeMillis() - onboardingTime > 1000) {
            if (totalPoints > 200)
                pointsWallet.setLoyaltyTier(LoyaltyTier.PLATINUM);
            else if (totalPoints > 100)
                pointsWallet.setLoyaltyTier(LoyaltyTier.GOLD);
        }
    }

}
