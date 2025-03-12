public class CurrentAccount extends BankAccount implements AccountOperations {
    private static final double OVERDRAFT_LIMIT = -1000.0;

    public CurrentAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0) {
            if (balance - amount < OVERDRAFT_LIMIT) {
                throw new InsufficientBalanceException("Withdrawal exceeds overdraft limit of " + OVERDRAFT_LIMIT);
            }
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Current");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Holder Name: " + getAccountHolderName());
        System.out.println("Balance: " + balance);
    }
}
