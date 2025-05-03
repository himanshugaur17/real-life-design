package loyalty.program.service;

import loyalty.program.exceptions.InsufficientWalletBalanceException;
import loyalty.program.models.PointsWallet;

public interface WalletPointsRedemptionService {
    boolean redeemPoints(PointsWallet pointsWallet, int points) throws InsufficientWalletBalanceException;

}
