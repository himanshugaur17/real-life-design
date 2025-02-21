package service.strategy.pricing;

public class MiniPricingStrategy implements PricingStrategy {
    private double baseFare;

    public MiniPricingStrategy(double baseFare) {
        this.baseFare = baseFare;
    }

    @Override
    public double calculatePrice(int parkingHours) {
        return baseFare * (parkingHours + 1);
    }

    @Override
    public void setHourlyRate(double baseCharge) {
        this.baseFare = baseCharge;
    }

}
