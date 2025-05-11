package vending.machine.models;

public class Price {
    private final MonetaryAmount amount;

    public Price(MonetaryAmount amount) {
        this.amount = amount;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

}
