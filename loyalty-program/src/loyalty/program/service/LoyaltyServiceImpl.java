package loyalty.program.service;

import java.util.ArrayList;
import java.util.List;

import loyalty.program.models.Customer;
import loyalty.program.models.CustomerAction;
import loyalty.program.models.LoyaltyPoint;
import loyalty.program.models.PointType;
import loyalty.program.models.PointsWallet;
import loyalty.program.observer.PointsWalletObserver;
import loyalty.program.rules.RuleEngine;

public class LoyaltyServiceImpl implements LoyaltyService {
    private final RuleEngine ruleEngine;
    private final List<PointsWalletObserver> pointsWalletObservers;

    public LoyaltyServiceImpl(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
        this.pointsWalletObservers = new ArrayList<>();
    }

    @Override
    public void addLoyaltyPoints(CustomerAction customerAction) {
        List<LoyaltyPoint> loyaltyPoints = ruleEngine.apply(customerAction);
        PointsWallet customerWallet = customerAction.customer().getPointsWallet();
        for (LoyaltyPoint loyaltyPoint : loyaltyPoints)
            customerWallet.addPoints(loyaltyPoint.pointType(), loyaltyPoint.points());
        notifyObservers(customerWallet, customerAction.customer());
    }

    @Override
    public void notifyObservers(PointsWallet pointsWallet, Customer customer) {
        pointsWalletObservers.forEach(obs -> obs.notify(pointsWallet, customer));
    }

    @Override
    public double redeemLoyaltyPoints(PointType pointType, Customer customer, int points) {
        PointsWallet customerWallet = customer.getPointsWallet();
        double amount = customerWallet.deductPoints(pointType, points);
        notifyObservers(customer.getPointsWallet(), customer);
        return amount;
    }

    @Override
    public void register(PointsWalletObserver observer) {
        pointsWalletObservers.add(observer);
    }

    @Override
    public void deRegister(PointsWalletObserver observer) {
        pointsWalletObservers.remove(observer);
    }

}
