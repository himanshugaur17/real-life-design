package loyalty.program.models;

public interface TierCalculationData {
    long getTotalOrdersIn12Months(Customer customer);

    long getPointsEarnedIn12Months(Customer customer);

    long getTotalPoints(Customer customer);
}
