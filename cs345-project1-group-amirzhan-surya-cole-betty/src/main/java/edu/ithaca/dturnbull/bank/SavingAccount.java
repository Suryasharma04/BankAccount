package edu.ithaca.dturnbull.bank;

public class SavingAccount extends BankAccount {
    
    final double annualInterestRate = 0.05;
    double dailyWithdrawn;
    
    public SavingAccount(int accountID, double balance) {
        super(accountID, balance);
        //TODO Auto-generated constructor stub
    }


    public void compoundInterest() {
        int numberOfTimesInterestCompoundedPerYear = 1; 
        double newBalance = balance * Math.pow((1 + (annualInterestRate / numberOfTimesInterestCompoundedPerYear)),
                numberOfTimesInterestCompoundedPerYear);
        balance = newBalance;
    }
    
    
}