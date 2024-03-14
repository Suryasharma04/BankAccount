package edu.ithaca.dturnbull.bank;

import java.time.LocalDateTime;

public class BankAccount extends CentralBank{

    protected double balance;
    protected boolean isFrozen;
    protected int accountID;
    protected boolean closed;
    // should we hold account type here? (savings or checking)

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(int accountID, double balance) {
        if (isValidAccountId(accountID)) {
            this.accountID = accountID;
            this.balance = balance;
            this.isFrozen = false;
        } else {
            throw new IllegalArgumentException("Account ID: " + accountID + " is invalid, cannot create account");
        }
    }
    
    
    
    
    public static boolean isValidAccountId(int accountNumber) {
        // Check if the account number has exactly 8 digits
        if (String.valueOf(accountNumber).length() == 8) {
            return true;
        }
        return false;
    }
   

    public static boolean isValidPassword(final String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasDigit && hasSpecialChar;
    }

  
    /**
     * Checks if the provided email address is valid according to a set of criteria.
     * 
     * @param email
     * @return 'true' if the email address is valid, 'false' otherwise.
     */
    public static boolean isEmailValid(String email) {
        // cannot be empty
        if (email == null || email.isEmpty()) {
            return false;
        }

        // has to contain the @ symbol
        if (!email.contains("@")) {
            return false;
        }

        // cannot start with .-_#.. symbols
        if (email.startsWith(".") || email.startsWith("-") || email.startsWith("_") || email.contains("#")
                || email.contains("..")) {
            return false;
        }

        // has to have symbols before and after the @ symbol
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String localPart = parts[0];
        String domainPart = parts[1];

        // cannot end with the -._ symbols
        if (localPart.endsWith("-") || localPart.endsWith(".") || localPart.endsWith("_")
                || !domainPart.contains(".")) {
            return false;
        }
        

        // Check if underscore, period, or dash is followed by one or more letter or
        // number
        if (localPart.contains("_") && !localPart.matches(".*_[a-zA-Z0-9]+.*")) {
            return false;
        }
        if (localPart.contains(".") && !localPart.matches(".*\\.[a-zA-Z0-9]+.*")) {
            return false;
        }
        if (localPart.contains("-") && !localPart.matches(".*-[a-zA-Z0-9]+.*")) {
            return false;
        }

        // Check if the last portion of the domain is at least two characters
        String[] domainParts = domainPart.split("\\.");
        if (domainParts[domainParts.length - 1].length() < 2) {
            return false;
        }

        return true;
    }


    /*Getter for Frozen */
    public boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
    
    /*Getter for account Id */
    public int getAccountID() {
        return this.accountID;
    }
/*getter for balance */
    protected double getBalance() {
        return balance;
    }
    
    /**
     * @throws Exception
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    protected void withdraw(double amount) throws Exception {
        Transaction transaction = new Transaction(LocalDateTime.now(), amount, this);
        transactionHistory.put(accountID, transaction);
        transaction.withdraw(amount);
    }

    /* Deposit method depending on transaction class */
    protected void deposit(double amount) throws Exception {
        Transaction transaction = new Transaction(LocalDateTime.now(), amount, this);
        transactionHistory.put(accountID, transaction);
        transaction.deposit(amount);
    }
    
    /* Transfer method depending on transaction class */
    protected void transfer(BankAccount account, double amount) throws Exception {
        Transaction transaction = new Transaction(LocalDateTime.now(), amount, this, account);
        transactionHistory.put(accountID, transaction);
        transaction.transfer(amount);
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}