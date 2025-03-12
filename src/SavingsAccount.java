public class SavingsAccount extends BankAccount implements AccountOperations {
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolderName) {
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
            if (balance - amount < MIN_BALANCE) {
                throw new InsufficientBalanceException("Insufficient balance! Minimum balance of " + MIN_BALANCE + " required.");
            }
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Savings");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Holder Name: " + getAccountHolderName());
        System.out.println("Balance: " + balance);
    }
}
