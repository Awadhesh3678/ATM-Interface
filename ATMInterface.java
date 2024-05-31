
import java.util.Scanner;

class UserAccount {
    private double balance;

    public UserAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        UserAccount piggyBank = new UserAccount(1000);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM!");
            System.out.println("");
            System.out.println("PRESS 1 TO :-  Withdraw");
            System.out.println("");
            System.out.println("PRESS 2 TO :- Deposit");
            System.out.println("");
            System.out.println("PRESS 3 TO :- Check balance");
            System.out.println("");
            System.out.println("PRESS 4 TO :- Quit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter the amount to withdraw: ");
                double amount = scanner.nextDouble();
                if (piggyBank.withdraw(amount)) {
                    System.out.printf("Successfully withdrew %.2f%n", amount);
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else if (choice == 2) {
                System.out.print("Enter the amount to deposit: ");
                double amount = scanner.nextDouble();
                piggyBank.deposit(amount);
                System.out.printf("Successfully deposited %.2f%n", amount);
            } else if (choice == 3) {
                System.out.printf("Your current balance is: %.2f%n", piggyBank.getBalance());
            } else if (choice == 4) {
                System.out.println("Thank you for using the ATM!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}