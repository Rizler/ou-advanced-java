package maman12_2;

public abstract class BankAccount {
    protected String accountNumber;
    protected String AccountOwner;
    protected String ownerId;
    protected double balance;

    public BankAccount(String accountNumber, String AccountOwner, String ownerId, double balance) {
        this.accountNumber = accountNumber;
        this.AccountOwner = AccountOwner;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountOwner() {
        return AccountOwner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountOwner(String accountOwner) {
        AccountOwner = accountOwner;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws IllegalBalance {
        if (balance - amount < 0) {
            throw new IllegalBalance();
        }
        this.balance -= amount;
    }

    public abstract void monthlyManagement();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BankAccount) {
            BankAccount other = (BankAccount) obj;
            return (this.accountNumber.equals(other.accountNumber) &&
                    this.AccountOwner.equals(other.AccountOwner) &&
                    this.ownerId.equals(other.ownerId) &&
                    this.balance == other.balance);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account number: " + accountNumber + "\n" +
                "Account owner: " + AccountOwner + "\n" +
                "Owner ID: " + ownerId + "\n" +
                "Balance: " + balance + "\n";
    }
}
