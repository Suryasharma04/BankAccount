package edu.ithaca.dturnbull.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CentralBank {
    protected HashMap<Integer, Transaction> transactionHistory;
    protected ArrayList<Admin> adminList;
    protected ArrayList<BankTeller> tellerList;
    protected ArrayList<User> userList;
    protected double overallAmount;
    
    protected CentralBank(){
        this.transactionHistory = new HashMap<>();
        this.adminList = new ArrayList<>();
        this.tellerList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    // Method to print details of each admin in adminList
    protected void printAdminListDetails() {
        for (Admin admin : adminList) {
            System.out.println("Admin Email: " + admin.getEmailAddress());
            System.out.println("Admin Password: " + admin.getPassword());
        }
    }

    // Method to print details of each teller in tellerList
    protected void printTellerListDetails() {
        for (BankTeller teller : tellerList) {
            System.out.println("Teller Email: " + teller.getEmailAddress());
            System.out.println("Teller Password: " + teller.getPassword());
        }
    }

    // Method to print details of each user in userList
    protected void printUserListDetails() {
        for (User user : userList) {
            System.out.println("User Email: " + user.getEmailAddress());
            System.out.println("User Password: " + user.getPassword());
            System.out.println("User Bank Accounts:");

            for (BankAccount account : user.getAccountList()) {
                System.out.println("   Account ID: " + account.getAccountID());
                System.out.println("   Balance: " + account.getBalance());
            }
        }
    }  

    protected double getOverallAmount(){
        ArrayList<User> users = this.getUserList();
        double overallAmount = 0;

        for (User user: users){
            List<BankAccount> accounts = user.getAccountList();
            for (BankAccount account: accounts){
                if(!account.getIsFrozen()){
                    overallAmount += account.getBalance();
                }
            }
        }
        return overallAmount;
    }

    // Method to add a new admin to adminList
    protected void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    // Method to add a new teller to tellerList
    protected void addBankTeller(BankTeller teller) {
        tellerList.add(teller);
    }

    // Method to add a new user to userList
    protected void addUser(User user) {
        userList.add(user);
    }

    protected ArrayList<User> getUserList(){
        return userList;
    }

    protected ArrayList<Admin> getAdminList(){
        return adminList;
    }

    protected ArrayList<BankTeller> getTellerList(){
        return tellerList;
    }

    protected HashMap<Integer, Transaction> getTransactionHistory(){
        return transactionHistory;
    }

    protected User getUser(int index){
        return userList.get(index);
    }
    

    protected User findUser(String username, String password) { 
        for (User user : userList) {
            if (user.getEmailAddress().equals(username) && user.getPassword().equals(password)) { 
                return user; 
            } 
        } 
        return null;
    }

    protected Admin findAdmin(String username, String password) { 
        for (Admin admin : adminList) {
            if (admin.getEmailAddress().equals(username) && admin.getPassword().equals(password)) { 
                return admin; 
            } 
        } 
        return null;
    }

    protected BankTeller findTeller(String username, String password) { 
        for (BankTeller teller : tellerList) {
            if (teller.getEmailAddress().equals(username) && teller.getPassword().equals(password)) { 
                return teller; 
            } 
        } 
        return null;
    }
    /*
     * Returns true if there is a user object in userList that has the email address that was input
     * Returns false if there is no user object with the input email address
     */
    protected boolean confirmEmailAddress(String emailAddress) {
        for (User user : userList) {
            if (user.emailAddress.equals(emailAddress)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Returns true if there is a user object with the input emailAddress that has the input password as well
     * Returns false if the user object with the input email adress does not have the input password
     */
    protected boolean confirmPassword(String emailAddress, String password) {
        for (User user : userList) {
            if (user.emailAddress.equals(emailAddress)) {
                if (user.password.equals(password)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /*
     * searches through each user in the user list
     * for each user it will search their bankAccount list for the account with the accountId provided
     * returns the account associated with the provided accountId if it exists
     * Throws exception if there is no account with that accountId in the bank
     */
    protected BankAccount findAccountById(int accountIdToFind) {
        for (User user : userList) {
            for (BankAccount account : user.bankAccountList) {
                if (account.accountID == accountIdToFind) {
                    return account;
                }
            }
        }
        throw new Error("No such account");
    }

    public void openAccount(double initialBalance) {
        // Instantiate a BankTeller
        BankTeller bankTeller = new BankTeller("", "", this);
        // Use the BankTeller's openAccount method
        bankTeller.openAccount(initialBalance);
    }

    public void closeAccount(BankAccount account) {
        // Instantiate a BankTeller
        BankTeller bankTeller = new BankTeller("", "", this);
        // Use the BankTeller's closeAccount method
        bankTeller.closeAccount(account);
    }



    public boolean isSuspicious() {
        // Get the transaction history of this account
        HashMap<Integer, Transaction> transactionHistory = getTransactionHistory();

        // If there are no transactions or only a few, it's not suspicious
        if (transactionHistory.size() <= 3) {
            return false;
        }

        // Get the list of transactions
        List<Transaction> transactions = new ArrayList<>(transactionHistory.values());

        // Calculate the average transaction amount
        double totalAmount = 0;
        for (Transaction transaction : transactions) {
            totalAmount += transaction.getAmount();
        }
        double averageAmount = totalAmount / transactions.size();

        // Check for any transactions significantly higher than the average
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > averageAmount * 2) {
                return true; // If any transaction is more than double the average, consider it suspicious
            }
        }

        // If no suspicious activity found
        return false;
    }
}
