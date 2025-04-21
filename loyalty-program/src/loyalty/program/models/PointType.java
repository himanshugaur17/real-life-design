package loyalty.program.models;

public enum PointType {
    STANDARD("Standard", 0.8),
    PROMOTIONAL("Promotiona,", 1);

    private final String pointType;
    private final double factor;

    PointType(String pointType, double factor) {
        this.pointType = pointType;
        this.factor = factor;
    }

    public double getAmount(int points) {
        return this.factor * points;
    }

}
