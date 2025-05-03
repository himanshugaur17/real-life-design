package loyalty.program.service;

import java.util.List;

import loyalty.program.exceptions.InsufficientWalletBalanceException;
import loyalty.program.models.Customer;
import loyalty.program.models.Order;
import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;
import loyalty.program.points.rule.engine.PointAllocationRuleEngine;
import loyalty.program.repos.PointsWalletRepository;

public class LoyaltyServiceImpl implements LoyaltyService {
    private final PointAllocationRuleEngine pointAllocationRuleEngine;
    private final PointsWalletRepository pointsWalletRepository;
    private final WalletPointsExpireService walletPointsExpireService;
    private final WalletPointsRedemptionService walletPointsRedemptionService;

    public LoyaltyServiceImpl(PointAllocationRuleEngine pointAllocationRuleEngine,
            PointsWalletRepository pointsWalletRepository,
            WalletPointsExpireService walletPointsExpireService,
            WalletPointsRedemptionService walletPointsRedemptionService) {
        this.pointAllocationRuleEngine = pointAllocationRuleEngine;
        this.pointsWalletRepository = pointsWalletRepository;
        this.walletPointsExpireService = walletPointsExpireService;
        this.walletPointsRedemptionService = walletPointsRedemptionService;
    }

    @Override
    public void addPoints(Order order) {
        Customer customer = order.customer();
        PointsWallet pointsWallet = pointsWalletRepository.getPointsWallet(customer.getId());
        List<Point> points = pointAllocationRuleEngine.allocatePoints(order, pointsWallet);
        pointsWalletRepository.addPoints(pointsWallet.getId(), points);
    }

    @Override
    public PointsWallet getPointsWallet(Customer customer) {
        PointsWallet pointsWallet = pointsWalletRepository.getPointsWallet(customer.getId());
        walletPointsExpireService.expireWalletPoints(pointsWallet);
        return pointsWallet;
    }

    @Override
    public boolean redeemPoints(Customer customer, int points) throws InsufficientWalletBalanceException {
        PointsWallet pointsWallet = pointsWalletRepository.getPointsWallet(customer.getId());
        return walletPointsRedemptionService.redeemPoints(pointsWallet, points);

    }
}