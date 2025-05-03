package loyalty.program.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loyalty.program.models.Point;
import loyalty.program.models.PointsWallet;

public class InMemoryPointsWalletRepoImpl implements PointsWalletRepository {
    private final Map<String, PointsWallet> pointsWalletsByCustomerId = new HashMap<>();
    private final Map<String, PointsWallet> pointsWalletsById = new HashMap<>();

    @Override
    public PointsWallet getPointsWallet(String customerId) {
        return pointsWalletsByCustomerId.get(customerId);
    }

    @Override
    public void addPoints(String pointWalletId, List<Point> points) {
        PointsWallet pointsWallet = pointsWalletsById.get(pointWalletId);
        pointsWallet.addPoint(points);
    }

}
