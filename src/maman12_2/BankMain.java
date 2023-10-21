package maman12_2;

public class BankMain {
    public static void main(String[] args) {
        /*
         * Create an array of 5 different bank accounts and perform the following operations on each account:
         * 1. Withdraw a random amount between 100 and 9000
         * 2. Deposit a random amount between 100 and 9000
         */
        BankAccount[] accounts = new BankAccount[5];
        accounts[0] = new ServiceChargeChecking("123", "John", "123456789", 1000, 10);
        accounts[1] = new NoServiceChargeChecking("456", "Jane", "987654321", 2000);
        accounts[2] = new InterestChecking("789", "Jack", "123456789", 3000);
        accounts[3] = new SavingsAccount("012", "Jill", "987654321", 4000);
        accounts[4] = new HighInterestSavings("345", "James", "123456789", 5000);

        for (BankAccount account : accounts) {
            System.out.println(account.getClass() + ":\n");
            System.out.println("Initial status:\n" + account);
            double withdrawAmount = 100 + Math.random() * 9000;
            try {
                account.withdraw(withdrawAmount);
                System.out.println("\nStatus after withdraw:\n" + account);
            } catch (IllegalBalance e) {
                System.out.println("\nIllegal balance. Can't withdraw " + withdrawAmount);
            }
            account.deposit(100 + Math.random() * 9000);
            System.out.println("\nStatus  after deposit:\n" + account);
            account.monthlyManagement();
            System.out.println("\nStatus after monthly manage:\n" + account);
            System.out.println("\n-----------------------\n");
        }
    }
}
