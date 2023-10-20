package maman12_2;

import java.util.Objects;

public class SavingsAccount extends BankAccount {

        protected static final double DEFAULT_INTEREST_RATE = 0.3;

        protected double interestRate;

        public SavingsAccount(String accountNumber, String AccountOwner, String ownerId, double balance,
                            double interestRate) {
            super(accountNumber, AccountOwner, ownerId, balance);
            this.interestRate = interestRate;
        }

        public SavingsAccount(String accountNumber, String AccountOwner, String ownerId, double balance) {
            this(accountNumber, AccountOwner, ownerId, balance, DEFAULT_INTEREST_RATE);
        }

    @Override
    public void monthlyManagement() {
        balance += balance * interestRate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SavingsAccount)) {
            return false;
        }
        return super.equals(obj) && interestRate == ((SavingsAccount) obj).interestRate;
    }

    @Override
    public String toString() {
        return super.toString() + " interest rate: " + interestRate;
    }
}
