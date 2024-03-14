package edu.ithaca.dturnbull.bank;

import java.util.*;

/**
 * Represents a bank teller user within the banking system.
 * Bank tellers have the authority to open and close bank accounts.
 */
public class BankTeller extends User {
    private List<BankAccount> bankAccountList; // List to store bank accounts managed by the teller

    /**
     * Constructor for a BankTeller object.
     * @param emailAddress The email address of the bank teller.
     * @param password The password of the bank teller.
     * @param centralBank The CentralBank instance associated with the bank teller.
     */
    public BankTeller(String emailAddress, String password, CentralBank centralBank) {
        super(emailAddress, password, centralBank);
        bankAccountList = new ArrayList<>(); // Initialize the bank account list
    }

    /**
     * Retrieves a list of suspicious accounts from the central bank.
     * @param centralBank The central bank instance.
     * @return A list of suspicious accounts.
     */
    public List<String> requestSuspiciousAccounts(CentralBank centralBank) {
        List<String> suspiciousAccounts = new ArrayList<>();
        // Logic to identify and add suspicious accounts to the list
        return suspiciousAccounts;
    }

    /**
     * Closes a specified bank account.
     * @param account The bank account to close.
     * @throws IllegalArgumentException if the account is null or frozen.
     */
    public void closeAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        } else if (account.getIsFrozen()) {
            throw new IllegalArgumentException("Account is frozen.");
        } else {
            bankAccountList.remove(account);
        }
    }

    /**
     * Opens a new bank account with the specified initial balance.
     * @param initialBalance The initial balance of the new account.
     */
    public void openAccount(double initialBalance) {
        int accountID;
        // Generate a unique account ID for the new account
        do {
            accountID = generateNewAccount();
        } while (isAccountOpen(accountID)); 
    
        // Create a new bank account with the generated ID and initial balance
        BankAccount newAccount = new BankAccount(accountID, initialBalance);
        bankAccountList.add(newAccount); // Add the new account to the teller's list
    
        System.out.println("Account opened successfully with ID: " + accountID);
    }
    
    /**
     * Checks if an account with the specified ID is already open.
     * @param accountID The ID of the account to check.
     * @return true if the account is already open, false otherwise.
     */
    private boolean isAccountOpen(int accountID) {
        for (BankAccount account : bankAccountList) {
            if (account.getAccountID() == accountID) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Generates a new unique account ID.
     * @return A new unique account ID.
     */
    private int generateNewAccount() {
        Random random = new Random();
        int accountID;
        // Generate a random account ID until a unique one is found
        do {
            accountID = random.nextInt(90000000) + 10000000;
        } while (!BankAccount.isValidAccountId(accountID));
        return accountID;
    }

    /**
     * Retrieves the list of bank accounts managed by the bank teller.
     * @return The list of bank accounts managed by the bank teller.
     */
    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }
}
