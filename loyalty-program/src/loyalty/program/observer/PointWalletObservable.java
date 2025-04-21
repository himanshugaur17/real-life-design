package loyalty.program.observer;

import loyalty.program.models.Customer;
import loyalty.program.models.PointsWallet;

public interface PointWalletObservable {
    void register(PointsWalletObserver observer);

    void deRegister(PointsWalletObserver observer);

    void notifyObservers(PointsWallet pointsWallet, Customer customer);
}
