import java.util.InputMismatchException; // Import exception class for handling invalid input
import java.util.Scanner; // Import the Scanner class to take input from the user

// Class representing a simple bank account
public class BankAccount {
    private double balance; // Instance variable to store the account balance

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        // Check if the initial balance is valid (non-negative)
        if (initialBalance >= 0) {
            this.balance = initialBalance; // Set the initial balance
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0; // Set to 0 if the input is invalid
        }
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        // Check if the deposit amount is positive
        if (amount > 0) {
            balance += amount; // Increase the balance by the deposit amount
            System.out.println("Deposited: $" + amount); // Print confirmation
        } else {
            System.out.println("Deposit amount must be positive."); // Handle invalid input
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        // Check if the withdrawal amount is positive and does not exceed the balance
        if (amount > 0 && amount <= balance) {
            balance -= amount; // Decrease the balance by the withdrawal amount
            System.out.println("Withdrew: $" + amount); // Print confirmation
        } else if (amount > balance) {
            System.out.println("Insufficient balance."); // Handle overdraft attempt
        } else {
            System.out.println("Withdrawal amount must be positive."); // Handle invalid input
        }
    }

    // Method to check and display the current balance
    public void checkBalance() {
        System.out.println("Current balance: $" + balance); // Print current balance
    }

    // Main method to execute the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt user for the initial balance and read input
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        // Create a BankAccount object with the initial balance provided by the user
        BankAccount account = new BankAccount(initialBalance);

        boolean running = true; // Flag to keep the program running

        // Loop to display the menu and handle user choices
        while (running) {
            // Display the menu
            System.out.println("Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt(); // Try reading the user's choice

                switch (choice) {
                    case 1: //handle deposit
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble(); //read deposit amount
                        account.deposit(depositAmount); //call deposit method
                        break;
                    case 2: //handle withdraw
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble(); //read withdraw amount
                        account.withdraw(withdrawAmount); //call withdraw method
                        break;
                    case 3: //handle check balance
                        account.checkBalance(); //call check balance method
                        break;
                    case 4: // Exit the program
                        running = false; // Set running to false to stop the loop
                        System.out.println("Exiting. Thank you!"); // Print exit message
                        break;
                    default: // Handle invalid menu choice
                        System.out.println("Invalid choice. Please try again."); // Print error message55
                        break;
                }
            } catch (InputMismatchException e) { //Preventing the choice input of being a double
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
            }
        }

        scanner.close(); // Close the scanner to avoid resource leak
    }
}