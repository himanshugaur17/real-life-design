package loyalty.program.service;

import java.time.LocalDate;
import java.util.Collections;

import loyalty.program.models.PointsWallet;

public class WalletPointsExpirationServiceImpl implements WalletPointsExpireService {

    @Override
    public void expireWalletPoints(PointsWallet pointsWallet) {
        pointsWallet.getPoints().stream().filter(point -> point.expirationDate().isBefore(LocalDate.now()))
                .forEach(point -> pointsWallet.removePoints(Collections.singletonList(point)));
    }

}
