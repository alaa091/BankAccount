import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Class representing a simple bank account
public class BankAccount {
    private User user; // Associate a user with the account
    private double balance; // Instance variable to store the account balance

    // Constructor to initialize the account with a user and an initial balance
    public BankAccount(User user, double initialBalance) {
        this.user = user;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to check and display the current balance
    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    // Separate class for managing user registration and login
    public static class BankApplication {
        private static List<User> users = new ArrayList<>();

        public static void registerUser(String username, String password) {
            users.add(new User(username, password));
            System.out.println("User registered successfully.");
        }

        public static User loginUser(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("Login successful.");
                    return user;
                }
            }
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    // Main method to execute the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User registration
        System.out.print("Register username: ");
        String username = scanner.nextLine();
        System.out.print("Register password: ");
        String password = scanner.nextLine();
        BankApplication.registerUser(username, password);

        // Loop to ensure valid initial balance input
        double initialBalance = -1; // Initialize with an invalid value to enter the loop
        while (initialBalance < 0) {
            System.out.print("Enter initial balance: ");
            try {
                initialBalance = scanner.nextDouble(); //prompt initial balance
                if (initialBalance < 0) {
                    System.out.println("Initial balance cannot be negative. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        // Print the confirmation message after a valid initial balance is entered
        System.out.println("Your account has been created with your initial balance!");
        scanner.nextLine(); // Consume the leftover newline

        // User login
        User loggedInUser = null;

        while (loggedInUser == null){
            System.out.print("Login username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Login password: ");
            String loginPassword = scanner.nextLine();
            loggedInUser = BankApplication.loginUser(loginUsername, loginPassword);
        }

        BankAccount account = new BankAccount(loggedInUser, initialBalance);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount;
                    try {
                        depositAmount = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount;
                    try {
                        withdrawAmount = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            scanner.nextLine(); // Consume the leftover newline
        }

        scanner.close();
    }
}
