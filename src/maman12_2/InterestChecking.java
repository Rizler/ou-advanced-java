package maman12_2;

public class InterestChecking extends NoServiceChargeChecking {
    protected static final double DEFAULT_INTEREST_RATE = 0.5;
    protected static final double DEFAULT_MINIMAL_BALANCE = 1000;
    protected double interestRate;

    public InterestChecking(String accountNumber, String AccountOwner, String ownerId, double balance,
                            double minimalBalance, double interestRate) {
        super(accountNumber, AccountOwner, ownerId, balance, minimalBalance);
        this.interestRate = interestRate;
    }

    public InterestChecking(String accountNumber, String AccountOwner, String ownerId, double balance,
                            double interestRate) {
        this(accountNumber, AccountOwner, ownerId, balance, DEFAULT_MINIMAL_BALANCE, interestRate);
    }

    public InterestChecking(String accountNumber, String AccountOwner, String ownerId, double balance) {
        this(accountNumber, AccountOwner, ownerId, balance, DEFAULT_MINIMAL_BALANCE, DEFAULT_INTEREST_RATE);
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void monthlyManagement() {
        balance += balance * interestRate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InterestChecking)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() + " interest rate: " + interestRate;
    }
}