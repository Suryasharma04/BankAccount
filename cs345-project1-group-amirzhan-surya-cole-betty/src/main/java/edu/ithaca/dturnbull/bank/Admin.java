package edu.ithaca.dturnbull.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an administrator user within the banking system.
 * Admins have special privileges such as checking overall bank balance and managing accounts.
 */
public class Admin extends User {

    /**
     * Constructor for an Admin object.
     * @param emailAddress The email address of the administrator.
     * @param password The password of the administrator.
     * @param centralBank The CentralBank instance associated with the administrator.
     */
    public Admin(String emailAddress, String password, CentralBank centralBank) {
        super(emailAddress, password, centralBank);
    }

    /**
     * Checks the overall balance of all accounts in the central bank.
     * @param centralBank The central bank instance.
     * @return The overall balance of all accounts.
     */
    public double checkOverallAmount(CentralBank centralBank){
        double amount = centralBank.getOverallAmount();
        return amount;
    }

    /**
     * Retrieves a list of suspicious accounts from the central bank.
     * @param centralBank The central bank instance.
     * @return A list of suspicious accounts.
     */
    public List<String> requestSuspiciousAccounts(CentralBank centralBank){
        List<String> suspiciousAccounts = new ArrayList<String>();
        // Logic to identify and add suspicious accounts to the list
        return suspiciousAccounts;
    }

    /**
     * Freezes a specified bank account.
     * @param account The bank account to freeze.
     */
    public void freezeAccount(BankAccount account){
        // Freeze the specified account
        account.setIsFrozen(true);
    }

    /**
     * Unfreezes a specified bank account.
     * @param account The bank account to unfreeze.
     */
    public void unfreezeAccount(BankAccount account){
        // Unfreeze the specified account
        account.setIsFrozen(false);
    }
}
