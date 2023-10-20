package maman12_2;

public class NoServiceChargeChecking extends CheckingAccount {
    protected static final double DEFAULT_MINIMAL_BALANCE = 100;
    protected double minimalBalance;

    NoServiceChargeChecking(String accountNumber, String AccountOwner, String ownerId, double balance,
                            double minimalBalance) {
        super(accountNumber, AccountOwner, ownerId, balance);
        this.minimalBalance = minimalBalance;
    }

    public NoServiceChargeChecking(String accountNumber, String AccountOwner, String ownerId, double balance) {
        this(accountNumber, AccountOwner, ownerId, balance, DEFAULT_MINIMAL_BALANCE);
    }

    public double getMinimalBalance() {
        return minimalBalance;
    }

    public void setMinimalBalance(double minimalBalance) {
        this.minimalBalance = minimalBalance;
    }

    @Override
    public void monthlyManagement() {
    }

    @Override
    public void withdraw(double amount) throws IllegalBalance {
        if (balance < minimalBalance) {
            throw new IllegalBalance();
        }
        super.withdraw(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NoServiceChargeChecking)) {
            return false;
        }
        return super.equals(obj) && minimalBalance == ((NoServiceChargeChecking) obj).minimalBalance;
    }

    @Override
    public String toString() {
        return super.toString() + " minimalBalance: " + minimalBalance;
    }
}
