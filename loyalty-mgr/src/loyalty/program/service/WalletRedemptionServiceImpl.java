package loyalty.program.service;

import loyalty.program.exceptions.InsufficientWalletBalanceException;
import loyalty.program.models.PointsWallet;

public class WalletRedemptionServiceImpl implements WalletPointsRedemptionService {

    @Override
    public boolean redeemPoints(PointsWallet pointsWallet, int points) throws InsufficientWalletBalanceException {
        long availablePoints = pointsWallet.getPointsBalance();
        if (availablePoints < points) {
            throw new InsufficientWalletBalanceException("Insufficient wallet balance");
        }
        while (!pointsWallet.redeemPoints(availablePoints, points)) {
            availablePoints = pointsWallet.getPointsBalance();
            if (availablePoints < points) {
                throw new InsufficientWalletBalanceException("Insufficient wallet balance");
            }
        }
        return true;
    }

}
