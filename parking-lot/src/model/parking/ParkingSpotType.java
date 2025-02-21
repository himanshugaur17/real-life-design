package model.parking;

import service.strategy.pricing.LargePricingStrategy;
import service.strategy.pricing.MiniPricingStrategy;
import service.strategy.pricing.PricingStrategy;

public enum ParkingSpotType {
    MINI(new MiniPricingStrategy(10)),
    LARGE(new LargePricingStrategy(20));

    private PricingStrategy pricingStrategy;

    ParkingSpotType(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStartegy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(int hours) {
        return this.pricingStrategy.calculatePrice(hours);
    }
}
