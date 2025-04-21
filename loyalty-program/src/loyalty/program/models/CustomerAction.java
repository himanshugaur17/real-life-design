package loyalty.program.models;

public record CustomerAction(Customer customer, String productPurchased, LoyaltyTier loyaltyTier, double amountSpent) {

}
