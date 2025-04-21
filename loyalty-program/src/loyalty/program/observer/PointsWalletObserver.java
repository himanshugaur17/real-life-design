package loyalty.program.observer;

import loyalty.program.models.Customer;
import loyalty.program.models.PointsWallet;

public interface PointsWalletObserver {

    void notify(PointsWallet pointsWallet, Customer customer);
}
