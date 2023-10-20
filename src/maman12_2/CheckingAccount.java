package maman12_2;

public abstract class CheckingAccount extends BankAccount {

    public CheckingAccount(String accountNumber, String AccountOwner, String ownerId, double balance) {
        super(accountNumber, AccountOwner, ownerId, balance);
    }

    public void writeCheck(double amount) throws IllegalBalance {
        if (balance - amount < 0) {
            throw new IllegalBalance();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CheckingAccount)) {
            return false;
        }
        return super.equals(obj);
    }
}
