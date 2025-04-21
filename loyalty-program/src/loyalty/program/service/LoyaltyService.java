package loyalty.program.service;

import loyalty.program.models.Customer;
import loyalty.program.models.CustomerAction;
import loyalty.program.models.PointType;
import loyalty.program.observer.PointWalletObservable;

public interface LoyaltyService extends PointWalletObservable {
    void addLoyaltyPoints(CustomerAction customerAction);

    double redeemLoyaltyPoints(PointType pointType, Customer customer, int points);
}
