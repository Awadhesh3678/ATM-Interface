import java.io.*;
import java.util.Scanner;

class UserAccount {
    private double balance;

    public UserAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("₹%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("₹%.2f withdrawn successfully.%n", amount);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void saveBalance(String username) {
        try {
            FileWriter writer = new FileWriter("balance_" + username + ".txt");
            writer.write(String.valueOf(balance));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving balance.");
        }
    }

    public static double loadBalance(String username) {
        try {
            File file = new File("balance_" + username + ".txt");
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                if (reader.hasNextDouble()) {
                    double bal = reader.nextDouble();
                    reader.close();
                    return bal;
                }
                reader.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading balance.");
        }
        return 1000.0; // default balance
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = "", password = "";
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.println("==== Welcome to the ATM System ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter new username: ");
                username = scanner.nextLine();
                System.out.print("Enter password: ");
                password = scanner.nextLine();

                if (isUserExists(username)) {
                    System.out.println("Username already exists. Try logging in.");
                } else {
                    saveUser(username, password);
                    System.out.println("Registration successful.");
                    isLoggedIn = true;
                }

            } else if (option == 2) {
                System.out.print("Enter username: ");
                username = scanner.nextLine();
                System.out.print("Enter password: ");
                password = scanner.nextLine();

                if (checkCredentials(username, password)) {
                    System.out.println("Login successful.");
                    isLoggedIn = true;
                } else {
                    System.out.println("Invalid username or password.");
                }

            } else {
                System.out.println("Invalid option.");
            }
        }

        UserAccount user = new UserAccount(UserAccount.loadBalance(username));
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\n==== ATM Menu ====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number (1–4).");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    if (scanner.hasNextDouble()) {
                        double amt = scanner.nextDouble();
                        user.withdraw(amt);
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    if (scanner.hasNextDouble()) {
                        double amt = scanner.nextDouble();
                        user.deposit(amt);
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.printf("Current balance: ₹%.2f%n", user.getBalance());
                    break;

                case 4:
                    user.saveBalance(username);
                    System.out.println("Logged out. Balance saved.");
                    sessionActive = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    public static void saveUser(String username, String password) {
        try {
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(username + "," + password + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    public static boolean isUserExists(String username) {
        try {
            File file = new File("users.txt");
            if (!file.exists()) return false;

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error checking user.");
        }
        return false;
    }

    public static boolean checkCredentials(String username, String password) {
        try {
            File file = new File("users.txt");
            if (!file.exists()) return false;

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error during login.");
        }
        return false;
    }
}
