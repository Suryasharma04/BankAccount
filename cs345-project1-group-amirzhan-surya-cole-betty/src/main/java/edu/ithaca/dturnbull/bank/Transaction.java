package edu.ithaca.dturnbull.bank;

import java.time.LocalDateTime;

/**
 * Represents a financial transaction within a bank.
 */
public class Transaction {

    /**
     * Enum to define the type of transaction.
     */
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER
    }
    
    protected LocalDateTime date; // Date and time of the transaction
    protected double amount; // Amount involved in the transaction
    protected BankAccount transferToAccount; // BankAccount to which transfer is made (for transfer transactions)
    protected BankAccount account; // BankAccount involved in the transaction

    /**
     * Constructor for a transaction, excluding transfers.
     * @param date The date and time of the transaction.
     * @param amount The amount involved in the transaction.
     * @param account The BankAccount involved in the transaction.
     */
    public Transaction(LocalDateTime date, double amount, BankAccount account) {
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    /**
     * Retrieves the amount involved in the transaction.
     * @return The amount involved in the transaction.
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Retrieves the BankAccount involved in the transaction.
     * @return The BankAccount involved in the transaction.
     */
    public BankAccount getAccount(){
        return this.account;
    }

    /**
     * Retrieves the date and time of the transaction.
     * @return The date and time of the transaction.
     */
    public LocalDateTime getDate(){
        return this.date;
    }

    /**
     * Constructor for a transaction, specifically for transfers.
     * @param date The date and time of the transaction.
     * @param amount The amount involved in the transaction.
     * @param account The BankAccount from which the transfer originates.
     * @param transferTo The BankAccount to which the transfer is made.
     */
    public Transaction(LocalDateTime date, double amount, BankAccount account, BankAccount transferTo) {
        this.date = date;
        this.amount = amount;
        this.transferToAccount = transferTo;
        this.account = account;
    }

    /**
     * Retrieves the BankAccount to which the transfer is made.
     * @return The BankAccount to which the transfer is made.
     */
    public BankAccount getTransferToAccount() {
        return transferToAccount;
    }

    /**
     * Deposits a specified amount into the associated account.
     * @param amount The amount to deposit.
     * @throws Exception if the account is frozen or the amount is invalid.
     */
    public void deposit(double amount) throws Exception {
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (amount > 0){
            account.balance += amount;
        }
        else {
            throw new IllegalArgumentException("Cannot deposit invalid amount");
        }
    }

    /**
     * Withdraws a specified amount from the associated account.
     * @param amount The amount to withdraw.
     * @throws Exception if the account is frozen, the amount is negative, or insufficient balance.
     */
    public void withdraw (double amount) throws Exception{
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > account.balance) {
            throw new IllegalArgumentException("Amount enter is greater than balance");
        }
        account.balance -= amount;
    }

    /**
     * Performs a transfer of a specified amount from one account to another.
     * @param amount The amount to transfer.
     * @throws Exception if either account is frozen, the transfer amount is negative, or there's insufficient balance.
     */
    public void transfer(double amount) throws Exception {
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (transferToAccount.getIsFrozen()) {
            throw new Exception("Transfer to account is frozen");
        }
        if (amount < 0){
            throw new IllegalArgumentException("Cannot transfer negative amount");
        }
        if (amount > account.balance) {
            throw new IllegalArgumentException("Amount enter is greater than balance");
        }
        account.withdraw(amount);
        transferToAccount.deposit(amount);
    }
}
