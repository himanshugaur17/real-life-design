package service.strategy.pricing;

public interface PricingStrategy {
    double calculatePrice(int parkingHours);
    void setHourlyRate(double baseCharge);
}
