package loyalty.program.service;

import loyalty.program.exceptions.InsufficientWalletBalanceException;
import loyalty.program.models.Customer;
import loyalty.program.models.Order;
import loyalty.program.models.PointsWallet;

public interface LoyaltyService {
    PointsWallet getPointsWallet(Customer customer);

    void addPoints(Order order);

    boolean redeemPoints(Customer customer, int points) throws InsufficientWalletBalanceException;

}
