package vending.machine.models;

public interface PaymentTender {
    MonetaryAmount getAmount();

    String getDescription();
}
