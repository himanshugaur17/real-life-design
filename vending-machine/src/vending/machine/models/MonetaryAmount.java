package vending.machine.models;

public class MonetaryAmount {
    private final double amount;
    private final Currency currency;

    public MonetaryAmount(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
