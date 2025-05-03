package loyalty.program.repos;

import java.util.List;

import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;

public interface PointsWalletRepository {
    PointsWallet getPointsWallet(String customerId);

    public void addPoints(String pointWalletId, List<Point> points);
}
