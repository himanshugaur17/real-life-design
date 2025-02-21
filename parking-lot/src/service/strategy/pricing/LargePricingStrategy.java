package service.strategy.pricing;

public class LargePricingStrategy implements PricingStrategy {
    private double baseFare;

    public LargePricingStrategy(double baseFare) {
        this.baseFare = baseFare;
    }

    @Override
    public double calculatePrice(int parkingHours) {
        return baseFare * parkingHours;
    }

    @Override
    public void setHourlyRate(double baseCharge) {
        this.baseFare = baseCharge;
    }

}
