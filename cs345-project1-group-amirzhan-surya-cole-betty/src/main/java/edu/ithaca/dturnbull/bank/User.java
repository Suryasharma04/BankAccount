package edu.ithaca.dturnbull.bank;

import java.util.ArrayList;

public class User /*extends CentralBank*/ {

    protected ArrayList<BankAccount> bankAccountList;
    protected String emailAddress;
    protected String password;
    // New stuff below
    protected CentralBank centralBank;

    public User(String emailAddress, String password, /* new*/CentralBank centralBank) {
        this.bankAccountList = new ArrayList<BankAccount>();
        this.emailAddress = emailAddress;
        this.password = password;
        // New stuff below
        this.centralBank = centralBank;
    }

    protected String getEmailAddress() {
        return emailAddress;
    }

    protected String getPassword() {
        return password;
    }

    protected ArrayList<BankAccount> getAccountList() {
        return bankAccountList;
    }

    protected boolean isAdmin() {
        for (Admin admin : centralBank.getAdminList()) {
            if (admin.getEmailAddress().equals(this.emailAddress)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isTeller() {
        for (BankTeller teller : centralBank.getTellerList()) {
            if (teller.getEmailAddress().equals(this.emailAddress)) {
                return true;
            }
        }
        return false;
    }

    protected void add(BankAccount account) {
        bankAccountList.add(account);
    }
    
}
