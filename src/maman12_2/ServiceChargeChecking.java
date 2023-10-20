package maman12_2;

public class ServiceChargeChecking extends CheckingAccount {

    protected static final double DEFAULT_MONTHLY_FEE = 10;

    protected double monthlyFee;

    public ServiceChargeChecking(String accountNumber, String AccountOwner, String ownerId, double balance,
                                 double monthlyFee) {
        super(accountNumber, AccountOwner, ownerId, balance);
        this.monthlyFee = monthlyFee;
    }

    public ServiceChargeChecking(String accountNumber, String AccountOwner, String ownerId, double balance) {
        this(accountNumber, AccountOwner, ownerId, balance, DEFAULT_MONTHLY_FEE);
    }


    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public void monthlyManagement() {
        balance -= monthlyFee;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceChargeChecking)) {
            return false;
        }
        return super.equals(obj) && monthlyFee == ((ServiceChargeChecking) obj).monthlyFee;
    }

    @Override
    public String toString() {
        return super.toString() + " monthlyFee: " + monthlyFee;
    }
}
