import java.util.ArrayList;  // Import for ArrayList
import java.util.Scanner;    // Import for Scanner

public class BankSystem {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: depositMoney(); break;
                    case 3: withdrawMoney(); break;
                    case 4: displayDetails(); break;
                    case 5: System.out.println("Thank you for using the Bank System!"); return;
                    default: System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to Bank System");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Display Account Details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account type (savings/current): ");
        String type = scanner.nextLine().toLowerCase();
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        if (type.equals("savings")) {
            accounts.add(new SavingsAccount(accNum, name));
        } else if (type.equals("current")) {
            accounts.add(new CurrentAccount(accNum, name));
        } else {
            System.out.println("Invalid account type!");
            return;
        }
        System.out.println("Account created successfully!");
    }

    private static BankAccount findAccount(String accNum) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNum)) {
                return account;
            }
        }
        return null;
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayDetails() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        ((AccountOperations)account).displayAccountDetails();
    }
}
