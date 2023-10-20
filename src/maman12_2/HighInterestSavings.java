package maman12_2;

import java.util.Objects;

public class HighInterestSavings extends SavingsAccount {
    protected static final double DEFAULT_INTEREST_RATE = 0.5;
    protected static final double DEFAULT_MIN_BALANCE = 1000;

    protected double minimalBalance;

    public HighInterestSavings(String accountNumber, String accountOwner, String ownerId, double balance, double interestRate, double minimalBalance) {
        super(accountNumber, accountOwner, ownerId, balance, minimalBalance);
        this.interestRate = interestRate;

    }

    public HighInterestSavings(String accountNumber, String accountOwner, String ownerId, double balance) {
        this(accountNumber, accountOwner, ownerId, balance, DEFAULT_INTEREST_RATE, DEFAULT_MIN_BALANCE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HighInterestSavings)) {
            return false;
        }
        return (super.equals(obj) && minimalBalance == ((HighInterestSavings) obj).minimalBalance);
    }

    @Override
    public String toString() {
        return super.toString() + " minimal balance: " + minimalBalance;
    }
}
