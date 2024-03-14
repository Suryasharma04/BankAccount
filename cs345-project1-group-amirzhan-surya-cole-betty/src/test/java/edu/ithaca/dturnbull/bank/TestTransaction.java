package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestTransaction {

    @Test
    void transferTest() throws Exception {
        
        // CHECKING ACCOUNT
        CheckingAccount testAccount = new CheckingAccount(12345678, 200.00);
        CheckingAccount testAccount2 = new CheckingAccount(12345679, 200.00);
        testAccount.setIsFrozen(true);
        
        // cannot transfer if from account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 100.00));
        assertEquals(200.00, testAccount.getBalance());
        assertEquals(200.00, testAccount2.getBalance());
        // cannot transfer 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.0));

        // cannot transfer just under 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, -0.01));

        // cannot transfer just above 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.01));

        // cannot transfer if to account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 100.00));

        // cannot transfer 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.0));

        // cannot transfer just above 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.01));

        // cannot tranfer just below 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, -0.01));

        // cannot transfer if both accounts are frozen
        testAccount.setIsFrozen(true);
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 100.00));

        // cannot transfer 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.0));

        // cannot transfer just above 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 0.01));

        // cannot transfer just below 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, -0.01));

        // cannot transfer negative amount
        testAccount.setIsFrozen(false);
        testAccount2.setIsFrozen(false);
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, -100.00));

        // cannot transfer just below zero
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, -0.01));

        // can transfer just above zero
        testAccount.transfer(testAccount2, 0.01);
        assertEquals(200.01, testAccount2.getBalance());
        assertEquals(199.99, testAccount.getBalance());

        // can transfer just below balance of from account
        testAccount.transfer(testAccount2, 199.98);
        assertEquals(.01, testAccount.getBalance(), .001);
        assertEquals(399.99, testAccount2.getBalance(), .001);

        // can transfer balance of from account
        testAccount2.transfer(testAccount, 399.99);
        assertEquals(400.00, testAccount.getBalance());
        assertEquals(0.00, testAccount2.getBalance());

        // cannot transfer just above balance of from account
        assertThrows(Exception.class, () -> testAccount.transfer(testAccount2, 400.01));

        // SAVING ACCOUNT

        CheckingAccount testAccount3 = new CheckingAccount(12345671, 200.00);
        CheckingAccount testAccount4 = new CheckingAccount(12345672, 200.00);
        testAccount3.setIsFrozen(true);
        
        // cannot transfer if from account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 100.00));

        // cannot transfer 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.0));
        
        // cannot transfer just under 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, -0.01));
        
        // cannot transfer just above 0 if from account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.01));

        // cannot transfer if to account is frozen
        testAccount4.setIsFrozen(true);
        testAccount3.setIsFrozen(false);
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 100.00));
        assertEquals(200.00, testAccount3.getBalance());
        assertEquals(200.00, testAccount4.getBalance());

        // cannot transfer 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.0));

        // cannot transfer just above 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.01));

        // cannot tranfer just below 0 if to account is frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, -0.01));

        // cannot transfer if both accounts are frozen
        testAccount3.setIsFrozen(true);
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 100.00));
    
        // cannot transfer 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.0));

        // cannot transfer just above 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 0.01));

        // cannot transfer just below 0 if both accounts are frozen
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, -0.01));

        // cannot transfer negative amount
        testAccount3.setIsFrozen(false);
        testAccount4.setIsFrozen(false);
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, -100.00));

        // cannot transfer just below zero
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, -0.01));

        // can transfer just above zero
        testAccount3.transfer(testAccount4, 0.01);
        assertEquals(200.01, testAccount4.getBalance());
        assertEquals(199.99, testAccount3.getBalance());

        // can transfer just below balance of from account
        testAccount3.transfer(testAccount4, 199.98);
        assertEquals(.01, testAccount3.getBalance(), .001);
        assertEquals(399.99, testAccount4.getBalance());

        // can transfer balance of from account
        testAccount4.transfer(testAccount3, 399.99);
        assertEquals(400.00, testAccount3.getBalance());
        assertEquals(0.00, testAccount4.getBalance());

        // cannot transfer just above balance of from account
        assertThrows(Exception.class, () -> testAccount3.transfer(testAccount4, 400.01));

    }

    @Test
    void depositTest() throws Exception {
        
        // CHECKING ACCOUNT
        CheckingAccount testAccount = new CheckingAccount(12345678, 200.00);
        testAccount.setIsFrozen(true);

        // cannot deposit if account is frozen
        assertThrows(Exception.class, () -> testAccount.deposit(100));
        
        // cannot deposit 0 if frozen
        assertThrows(Exception.class, () -> testAccount.deposit(0.0));

        // cannot deposit just above 0 if frozen
        assertThrows(Exception.class, () -> testAccount.deposit(.01));

        // cannot deposit just below 0 if frozen
        assertThrows(Exception.class, () -> testAccount.deposit(-.01));

        testAccount.setIsFrozen(false);
        // can deposit if account is not frozen
        testAccount.deposit(100.00);
        assertEquals(300.00, testAccount.getBalance());

        // cannot deposit if amount is less than 0
        assertThrows(Exception.class, () -> testAccount.deposit(-.01));
        
        // can deposit if amount is just above 0
        testAccount.deposit(0.01);
        assertEquals(300.01, testAccount.getBalance());

        // cannot deposit if amount is just below zero
        assertThrows(Exception.class, () -> testAccount.deposit(-.01));

        // SAVINGS ACCOUNT
        SavingAccount testAccount2 = new SavingAccount(12345679, 200.00);
        testAccount2.setIsFrozen(true);

        // cannot deposit if account is frozen
        assertThrows(Exception.class, () -> testAccount2.deposit(100));
        
        // cannot deposit 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.deposit(0.0));

        // cannot deposit just above 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.deposit(.01));

        // cannot deposit just below 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.deposit(-.01));

        testAccount2.setIsFrozen(false);
        // can deposit if account is not frozen
        testAccount2.deposit(100.00);
        assertEquals(300.00, testAccount2.getBalance());

        // cannot deposit if amount is less than 0
        assertThrows(Exception.class, () -> testAccount2.deposit(-.01));
        
        // can deposit if amount is just above 0
        testAccount2.deposit(0.01);
        assertEquals(300.01, testAccount2.getBalance());

        // cannot deposit if amount is just below zero
        assertThrows(Exception.class, () -> testAccount2.deposit(-.01));
    }

    @Test
    void withdrawTest() throws Exception {

        // CHECKING ACCOUNT
        CheckingAccount testAccount = new CheckingAccount(12345678, 200.00);
        testAccount.setIsFrozen(true);

        // cannot withdraw if account is frozen
        assertThrows(Exception.class, () -> testAccount.withdraw(100.00));
        
        // cannot withdraw 0 if frozen
        assertThrows(Exception.class, () -> testAccount.withdraw(0.0));

        // cannot withdraw just above 0 if frozen
        assertThrows(Exception.class, () -> testAccount.withdraw(0.01));

        // cannot withdraw just below 0 if frozen
        assertThrows(Exception.class, () -> testAccount.withdraw(-0.01));

        testAccount.setIsFrozen(false);
        // can withdraw valid amount if account is not frozen
        testAccount.withdraw(100.00);
        assertEquals(100.00, testAccount.getBalance());
        
        // cannot withdraw if amount is less than 0
        assertThrows(Exception.class, () -> testAccount.withdraw(-10));
        
        // can withdraw if amount is just above 0
        testAccount.withdraw(.01);
        assertEquals(99.99, testAccount.getBalance());
        
        // cannot withdraw if amount is just below zero
        assertThrows(Exception.class, () -> testAccount.withdraw(-0.01));

        // cannot withdraw if amount is just above balance
        assertThrows(Exception.class, () -> testAccount.withdraw(100.00));

        // cannot withdraw if amount is above balance
        assertThrows(Exception.class, () -> testAccount.withdraw(100000.00));

        // SAVING ACCOUNT
        SavingAccount testAccount2 = new SavingAccount(12345679, 200.00);
        testAccount2.setIsFrozen(true);

        // cannot withdraw if account is frozen
        assertThrows(Exception.class, () -> testAccount2.withdraw(100.00));
        
        // cannot withdraw 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.withdraw(0.0));

        // cannot withdraw just above 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.withdraw(0.01));

        // cannot withdraw just below 0 if frozen
        assertThrows(Exception.class, () -> testAccount2.withdraw(-0.01));

        testAccount2.setIsFrozen(false);
        // can withdraw valid amount if account is not frozen
        testAccount2.withdraw(100.00);
        assertEquals(100.00, testAccount2.getBalance());
        
        // cannot withdraw if amount is less than 0
        assertThrows(Exception.class, () -> testAccount2.withdraw(-10));
        
        // can withdraw if amount is just above 0
        testAccount2.withdraw(.01);
        assertEquals(99.99, testAccount2.getBalance());
        
        // cannot withdraw if amount is just below zero
        assertThrows(Exception.class, () -> testAccount2.withdraw(-0.01));

        // cannot withdraw if amount is just above balance
        assertThrows(Exception.class, () -> testAccount2.withdraw(100.00));

        // cannot withdraw if amount is above balance
        assertThrows(Exception.class, () -> testAccount2.withdraw(100000.00));
    }
}


