package loyalty.program.models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PointsWallet {
    private final Map<PointType, Integer> balanceMap = new ConcurrentHashMap<>();
    private LoyaltyTier loyaltyTier;

    public void addPoints(PointType pointType, int points) {
        balanceMap.computeIfAbsent(pointType, key -> 0);
        balanceMap.compute(pointType, (k, v) -> v + points);
    }

    public double deductPoints(PointType pointType, int points) {
        balanceMap.compute(pointType, (k, v) -> {
            if (v < points)
                throw new IllegalArgumentException("insufficient points");
            return v - points;
        });
        return pointType.getAmount(points);
    }

    public Map<PointType, Integer> getBalanceMap() {
        return balanceMap;
    }

    public LoyaltyTier getLoyaltyTier() {
        return loyaltyTier;
    }

    public void setLoyaltyTier(LoyaltyTier loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }
    
}
