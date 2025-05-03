package loyalty.program.service;

import loyalty.program.models.PointsWallet;

public interface WalletPointsExpireService {
    void expireWalletPoints(PointsWallet pointsWallet);
}
