package loyalty.program.models;

public class Customer {
    private final String custId;
    private final String name;
    /*
     * Alternative could have been to not let customer couple 
     * with points wallet, the loyalty service could have fetched the 
     * wallet using pointWalletDao.getWalletForCustomer(cusrtomerId)
     * internally the in-memory dao implementation would have Map<CustomerID, PointWallet>
     */
    private final PointsWallet pointsWallet;

    public PointsWallet getPointsWallet() {
        return pointsWallet;
    }

    private long onboardingTimeEpoch;

    public Customer(String custId, String name, PointsWallet pointsWallet) {
        this.custId = custId;
        this.name = name;
        this.pointsWallet = pointsWallet;
        this.onboardingTimeEpoch = System.currentTimeMillis();
    }

    public long getOnboardingTimeEpoch() {
        return onboardingTimeEpoch;
    }
}
