package edu.ithaca.dturnbull.bank;

public class CheckingAccount extends BankAccount{

    public CheckingAccount(int accountID, double balance) {
        super(accountID, balance);
        //TODO Auto-generated constructor stub
    }

    //@Override
    public void transfer(long balance, BankAccount account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }

    //@Override
    public void createAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }
    
}
