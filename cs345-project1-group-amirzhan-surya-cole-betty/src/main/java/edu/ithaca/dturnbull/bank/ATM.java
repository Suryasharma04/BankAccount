package edu.ithaca.dturnbull.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private CentralBank centralBank;
    private Scanner scanner;

    public ATM(CentralBank centralBank) {
        this.centralBank = centralBank;
        this.scanner = new Scanner(System.in);
    }
    
    public void startCLI() throws Exception {
        System.out.println("Welcome to the ATM!");
        boolean var = true;
        while (var) {
            System.out.println("Please specify your role - User, Admin, or BankTeller: ");
            String userInput = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
            
            if (!userInput.equals("user") && !userInput.equals("admin") && !userInput.equals("bankteller")) {
                System.out.println("Invalid role. Please specify User, Admin, or BankTeller.");
                continue; // Prompt user again for role input
            }
            
            System.out.println("Please enter your email address:");
            String emailAddress = scanner.nextLine().trim();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
    
            boolean loggedOut = false; // Flag to track logout status
    
            switch (userInput) {
                case "user":
                    if (authenticateUser(emailAddress, password)) {
                        userActions();
                        loggedOut = true; // Set flag to true if user logged out
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case "admin":
                    if (authenticateAdmin(emailAddress, password)) {
                        adminActions();
                        loggedOut = true; // Set flag to true if admin logged out
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case "bankteller":
                    if (authenticateBankTeller(emailAddress, password)) {
                        bankTellerActions();
                        loggedOut = true; // Set flag to true if bank teller logged out
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid role. Please specify User, Admin, or BankTeller.");
            }
    
            if (loggedOut) {
                // If the user has logged out, exit the loop
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }
        }
    }

    
    private void userActions() throws Exception {
        boolean var = true; 
        System.out.println("Welcome, User!");
        while (var) {
            System.out.println("Please choose an option:");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Check Balance");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Deposit");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
    
            String choiceInput = scanner.nextLine().replaceAll("\\s", ""); // Remove all whitespaces
    
            if (choiceInput.equalsIgnoreCase("logout")) {
                System.out.println("Thank you for visiting the bank. Goodbye!");
                var = false; 
                break;
            }
    
            switch (choiceInput.toLowerCase()) {
                case "1":
                case "1.":
                case "withdraw":
                case "withdrawmoney":
                    withdrawMoney();
                    break;
                
                case "2":
                case "2.":
                case "checkbalance":
                    checkBalance();
                    break;
                
                case "3":
                case "3.":
                case "transfer":
                    transferMoney();
                    break;
                
                case "4":
                case "4.":
                case "transaction":
                case "transactionhistory":
                    viewTransactionHistory();
                    break; 

                case "5":
                case "5.":
                case "deposit":
                    depositMoney();
                    break; 

                case "6":
                case "6.":
                case "logout":
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!continueActions()) {
                break;
            }
        }
    }
    
    
    private void adminActions() throws Exception {
        boolean var = true; 
        System.out.println("Welcome, Admin!");
        while (var) {
            System.out.println("Please choose an option:");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Freeze Account");
            System.out.println("6. Unfreeze Account");
            System.out.println("7. Check Overall Amount");
            System.out.println("8. Suspicious Activity");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");
    
            String choiceInput = scanner.nextLine().replaceAll("\\s", ""); // Remove all whitespaces
    
            if (choiceInput.equalsIgnoreCase("logout")) {
                System.out.println("Logged out");
                var = false; 
            }
    
            switch (choiceInput.toLowerCase()) {
                case "1":
                case "1.":
                case "withdrawmoney":
                case "withdraw":
                    withdrawMoney();;
                    break;

                case "2":
                case "2.":
                case "depositmoney":
                case "deposit":
                    depositMoney();
                    break;

                case "3":
                case "3.":
                case "transfermoney":
                case "transfer":
                    transferMoney();
                    break;

                case "4":
                case "4.":
                case "checkbalance":
                    checkBalance();
                    break;

                case "5":
                case "5.":
                case "freezeaccount":
                case "freeze":
                    freeze();
                    break;

                case "6":
                case "6.":
                case "unfreezeaccount":
                case "unfreeze":
                    unfreeze();
                    break;

                case "7":
                case "7.":
                case "checkoverallamount":
                case "checkoverall":
                    checkOverallAmount();
                    break;
                
                    
                case "8":
                case "8.":
                case "suspiciousactivity":
                    checkSuspiciousActivity();
                    break;

                case "9":
                case "9.":
                case "logout":
                    System.out.println("Logged out. Thank you for visiting the bank. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!continueActions()) {
                break;
            }
        }
    }

    
    private void bankTellerActions() {
        boolean var = true; 
        System.out.println("Welcome, Bank Teller!");
        while (var) {
            System.out.println("Please choose an option:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Open Account");
            System.out.println("4. Close Account");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
    
            String choiceInput = scanner.nextLine().replaceAll("\\s", ""); // Remove all whitespaces
    
            if (choiceInput.equalsIgnoreCase("logout")) {
                System.out.println("Logged out.");
                var = false; 
                break;
            }
    
            switch (choiceInput.toLowerCase()) {
                case "1":
                case "1.":
                case "depositmoney":
                case "deposit":
                    depositMoney();
                    break;
    
                case "2":
                case "2.":
                case "withdraw":
                case "withdrawmoney":
                    withdrawMoney();
                    break;
    
                case "3":
                case "3.":
                case "open":
                case "openaccount":
                    openAccount();
                    break;
    
                case "4":
                case "4.":
                case "close":
                case "closeaccount":
                    closeAccount();
                    break;
    
                case "5":
                case "5.":
                case "logout":
                    System.out.println("Logged out.");
                    return;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    
            if (!continueActions()) {
                break;
            }
        }
    }    
    

    private void viewTransactionHistory() {
        System.out.print("Enter your account ID: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
    
        // Retrieve transaction history for the specified account from Bank or CentralBank
        BankAccount account = centralBank.findAccountById(accountID);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
    
        HashMap<Integer, Transaction> transactionHistory = account.getTransactionHistory();
    
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found for account ID " + accountID);
        } else {
            System.out.println("Transaction History for Account ID " + accountID + ":");
            for (Transaction transaction : transactionHistory.values()) { // Iterate over values
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Amount: " + transaction.getAmount());
                if (transaction.getTransferToAccount() != null) {
                    System.out.println("Transfer from Account: " + transaction.getAccount().getAccountID());
                    System.out.println("Transfer to Account: " + transaction.getTransferToAccount().getAccountID());
                } else {
                    System.out.println("Account: " + transaction.getAccount().getAccountID());
                }
                System.out.println(); // Add a newline for formatting
            }
        }
    }

    
    private void openAccount() {
        System.out.println("Opening a new account...");
        System.out.print("Enter initial balance for the new account: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
    
        try {
            // Call the openAccount method of BankTeller
            centralBank.openAccount(initialBalance);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void closeAccount() {
        System.out.println("Closing an account...");
        System.out.print("Enter the account ID to close: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
    
        // Find the BankAccount object with the specified accountID
        BankAccount account = centralBank.findAccountById(accountID);
        if (account != null) {
            try {
                // Call the closeAccount method of BankTeller
                centralBank.closeAccount(account);
                System.out.println("Account closed successfully.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Account with ID " + accountID + " not found.");
        }
    }
    
    
    // Helper method to check if the user/admin wants to continue or logout
private boolean continueActions() {
    System.out.println("Do you want to perform any other task? (Yes/No): ");
    String input = scanner.nextLine().replaceAll("\\s", ""); // Remove all whitespaces
    if (input.equalsIgnoreCase("no")) {
        System.out.println("Logged out.");
        return false;
    } else if (input.equalsIgnoreCase("yes")) {
        return true;
    } else {
        System.out.println("Invalid input. Please indicate YES or NO.");
        return continueActions(); // Repeat the process until valid input is received
    }
}


private void checkOverallAmount() {
    double overallAmount = 0.0;
    // Iterate through each user's accounts and sum up the balances
    for (User user : centralBank.getUserList()) {
        for (BankAccount account : user.getAccountList()) {
            overallAmount += account.getBalance();
        }
    }
    System.out.println("Overall balance of all accounts: " + overallAmount);
}


private void transferMoney() throws Exception {
    BankAccount sourceAccount, destinationAccount;

    // Prompt user for source account ID
    do {
        System.out.print("Enter your source account ID: ");
        int sourceAccountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        sourceAccount = centralBank.findAccountById(sourceAccountID);
        if (sourceAccount == null) {
            System.out.println("Invalid source account ID. Please try again.");
        }
    } while (sourceAccount == null);

    // Prompt user for destination account ID
    do {
        System.out.print("Enter the destination account ID: ");
        int destinationAccountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        destinationAccount = centralBank.findAccountById(destinationAccountID);
        if (destinationAccount == null) {
            System.out.println("Invalid destination account ID. Please try again.");
        }
    } while (destinationAccount == null);

    // Prompt user for transfer amount
    System.out.print("Enter the amount to transfer: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    try {
        sourceAccount.withdraw(amount); // Withdraw from source account
        destinationAccount.deposit(amount); // Deposit into destination account

        System.out.println("Transfer successful.");
        System.out.println("Source account balance: " + sourceAccount.getBalance());
        System.out.println("Destination account balance: " + destinationAccount.getBalance());
    } catch (Exception e) {
        // Rollback the withdrawal if deposit fails
        sourceAccount.deposit(amount);
        System.out.println(e.getMessage());
    }
}

// Method to check for suspicious activity in a bank account
private void checkSuspiciousActivity() {
    System.out.print("Enter your account ID: ");
    int accountID = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    // Retrieve the bank account from the central bank
    BankAccount account = centralBank.findAccountById(accountID);
    if (account == null) {
        System.out.println("Invalid account ID. Please try again.");
        return;
    }

    // Check for suspicious activity
    if (account.isSuspicious()) {
        System.out.println("Suspicious activity detected in account ID " + accountID);
    } else {
        System.out.println("No suspicious activity detected in account ID " + accountID);
    }
}



// Method to allow a user to deposit money into their account
private void depositMoney() {
    BankAccount account;
    do {
        System.out.print("Enter your account ID: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        account = centralBank.findAccountById(accountID);
        if (account == null) {
            System.out.println("Invalid account ID. Please try again.");
        }
    } while (account == null);

    System.out.print("Enter the amount to deposit: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    try {
        account.deposit(amount);
        System.out.println("Deposit successful. Current balance: " + account.getBalance());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

// Method to check the balance of a user's account
private void checkBalance() {
    BankAccount account;
    do {
        System.out.print("Enter your account ID: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        account = centralBank.findAccountById(accountID);
        if (account == null) {
            System.out.println("Invalid account ID. Please try again.");
        }
    } while (account == null);

    System.out.println("Current balance: " + account.getBalance());
    }

    // Method that an admin will use to freeze an account
    private void freeze() {
        System.out.print("Enter 8 digit account ID of the account you wish to freeze: ");
        int accountIdToFreeze = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BankAccount account;

        try {
            account = centralBank.findAccountById(accountIdToFreeze);
        } catch (Error e) { //update this to correct exception
            System.out.println("There is no account with the ID: " + accountIdToFreeze);
            return;
        }
        if (account.getIsFrozen()) {
            System.out.println("Account: " + accountIdToFreeze + " is already frozen");
        }
        if (!account.getIsFrozen()) {
            account.setIsFrozen(true);
            if (account.getIsFrozen()) {
                System.out.println("Account: " + accountIdToFreeze + " has been frozen.");
            }
            else {
                System.out.println("There was an error while attempting to freeze account: " + accountIdToFreeze);
            }
        }
    }

    // Method that an admin will use to unfreeze an account
    private void unfreeze() {
        System.out.print("Enter 8 digit account ID of the account you wish to unfreeze: ");
        int accountIdToUnfreeze = scanner.nextInt();
        scanner.nextLine(); 

        BankAccount account;

        try {
            account = centralBank.findAccountById(accountIdToUnfreeze);
        } catch (Error e) { //update this to correct exception
            System.out.println("There is no account with the ID: " + accountIdToUnfreeze);
            return;
        }
        if (!account.getIsFrozen()) {
            System.out.println("Account: " + accountIdToUnfreeze + " is already unfrozen");
        }
        if (account.getIsFrozen()) {
            account.setIsFrozen(false);
            if (!account.getIsFrozen()) {
                System.out.println("Account: " + accountIdToUnfreeze + " has been unfrozen.");
            }
            else {
                System.out.println("There was an error while attempting to unfreeze account: " + accountIdToUnfreeze);
            }
        }
    }

    private boolean authenticateUser(String emailAddress, String password) {
        List<User> userList = centralBank.getUserList();
        for (User user : userList) {
            if (user.getEmailAddress().equals(emailAddress) && user.getPassword().equals(password)) {
                return true; 
            }
        }
        return false; 
    }
    private boolean authenticateAdmin(String emailAddress, String password) {
        List<Admin> adminList = centralBank.getAdminList();
        for (Admin admin : adminList) {
            if (admin.getEmailAddress().equals(emailAddress) && admin.getPassword().equals(password)) {
                return true; 
            }
        }
        return false; 
    }

    private boolean authenticateBankTeller(String emailAddress, String password) {
        List<BankTeller> tellerList = centralBank.getTellerList();
        for (BankTeller teller : tellerList) {
            if (teller.getEmailAddress().equals(emailAddress) && teller.getPassword().equals(password)) {
                return true; 
            }
        }
        return false; 
    }


    private void withdrawMoney() {
        BankAccount account;
        do {
            System.out.print("Enter your account ID: ");
            int accountID = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            account = centralBank.findAccountById(accountID);
            if (account == null) {
                System.out.println("Invalid account ID. Please try again.");
            }
        } while (account == null);
    
        // Check if the account is closed
        if (account.isClosed()) {
            System.out.println("Account is closed. Withdrawal not allowed.");
            return;
        }
    
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
    
        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        CentralBank centralBank = new CentralBank();

        // Creating dummy users
        User user1 = new User("user1@example.com", "password1", centralBank);
        User user2 = new User("user2@example.com", "password2", centralBank);

 
        // Adding dummy accounts for users
        user1.getAccountList().add(new BankAccount(12345678, 1000.0));
        user2.getAccountList().add(new BankAccount(87654321, 2000.0));

        // bank accounts
        BankAccount account1 = new BankAccount(12345678, 1000.0);
        BankAccount account2 = new BankAccount(87654321, 2000.0);
        // Adding accounts to the users
        user1.getAccountList().add(account1);
        user2.getAccountList().add(account2);
 
        // Adding users to the central bank
        centralBank.addUser(user1);
        centralBank.addUser(user2);


        // Creating dummy admin
        Admin admin1 = new Admin("admin@admin.com", "admin", centralBank);
        centralBank.addAdmin(admin1);

        // Creating dummy bank teller
        BankTeller teller1 = new BankTeller("teller@teller.com", "teller", centralBank);
        centralBank.addBankTeller(teller1);

        ATM atm = new ATM(centralBank);
        atm.startCLI();
    }
}