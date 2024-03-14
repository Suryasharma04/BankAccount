package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Test class for the BankAccount class.
 */
class BankAccountTest {

    /**
     * Test case for the isEmailValid method in the BankAccount class.
     * Tests various email formats to check validity.
     */
    @Test
    void isEmailValidTest() {
        // Test with valid and invalid prefix email format
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com")); // valid email address
        assertTrue(BankAccount.isEmailValid("abc@mail.com")); // valid email address
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com")); // valid email address
        assertFalse(BankAccount.isEmailValid(".abc@mail.com")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc-@mail.com")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com")); // invalid email address
        assertTrue(BankAccount.isEmailValid("a@b.com")); // valid email address
        assertFalse(BankAccount.isEmailValid("")); // empty string // invalid
        // Test with valid and invalid domain email format
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc")); // valid email address
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com")); // valid email address
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com")); // valid email address
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org")); // valid email address
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc.def@mail")); // invalid email address
        assertFalse(BankAccount.isEmailValid("abc.def@mail")); // invalid email address
    }

    /**
     * Test case for the isValidPassword method in the BankAccount class.
     * Tests various password formats to check validity.
     */
    @Test
    public void testValidate() {
        assertTrue(BankAccount.isValidPassword("password1!"));
        assertFalse(BankAccount.isValidPassword("password"));
        assertFalse(BankAccount.isValidPassword("password1"));
        assertFalse(BankAccount.isValidPassword("password!"));
        assertFalse(BankAccount.isValidPassword("pass1!"));
        assertFalse(BankAccount.isValidPassword("abc.def@mail..com")); // invalid email address
    }

    /**
     * Test case for the isValidAccountId method in the BankAccount class.
     * Tests various account ID formats to check validity.
     */
    @Test
    public void testValidAccount() {
        assertTrue(BankAccount.isValidAccountId(12248567));
        assertTrue(BankAccount.isValidAccountId(12345678));
        assertFalse(BankAccount.isValidAccountId(234764));
        assertFalse(BankAccount.isValidAccountId(234764567));
        assertFalse(BankAccount.isValidAccountId(122213333));
        assertFalse(BankAccount.isValidAccountId(122777855));
    }

    /**
     * Test case for the withdraw method in the BankAccount class.
     * It checks if the withdrawal is processed correctly under different scenarios:
     * - Valid withdrawal amount
     * - Withdrawal amount exceeding account balance
     * - Negative withdrawal amount
     * - Attempting withdrawal from a frozen account
     *
     * @throws Exception if an error occurs during the withdrawal process
     */
    @Test
    public void testWithdraw() throws Exception {
        LocalDateTime now= LocalDateTime.now();
        BankAccount account1 = new BankAccount(12345678, 400);
        Transaction transaction=new Transaction(now, 200, account1);

        // Testing valid withdrawal
        transaction.withdraw(200);
        assertEquals(200.0, account1.getBalance(), "After withdrawing 200.0 from 400.0, balance should be 200.0");

        // Testing withdrawal of amount greater than balance
        Exception exception = assertThrows(IllegalArgumentException.class, () -> transaction.withdraw(1000.0));
        assertTrue(exception.getMessage().contains("Amount enter is greater than balance"),
                "Should throw exception for withdrawing more than balance");

        // Testing negative withdrawal amount
        exception = assertThrows(IllegalArgumentException.class, () -> transaction.withdraw(-50.0));
        assertTrue(exception.getMessage().contains("Amount must be positive"),
                "Should throw exception for negative withdrawal amount");

        // Testing withdrawal from a frozen account
        account1.isFrozen = true;
        exception = assertThrows(Exception.class, () -> transaction.withdraw(50.0));
        assertTrue(exception.getMessage().contains("Account is frozen"),
                "Should throw exception for withdrawing from a frozen account");
    }
    
    /**
     * Test case for the deposit method in the BankAccount class.
     * It checks if the deposit is processed correctly under different scenarios:
     * - Valid deposit amount
     * - Negative deposit amount
     * - Attempting deposit to a frozen account
     *
     * @throws Exception if an error occurs during the deposit process
     */
    @Test
    public void testDeposit() throws Exception {
        BankAccount account = new BankAccount(12345678, 100); 

        // Testing valid deposit
        account.deposit(200.0);
        assertEquals(300.0, account.getBalance(), "After depositing 200.0, balance should be 300.0");

        // Testing negative deposit amount
        account.deposit(50.0);
        assertEquals(350.0, account.getBalance(), "After depositing another 50.0, balance should be 150.0");

        // Testing negative deposit amount
        Exception exception = assertThrows(IllegalArgumentException.class, ()->account.deposit(-50.0));
        assertTrue(exception.getMessage().contains("Cannot deposit invalid amount"));
        
        // Testing deposit to a frozen account
        account.isFrozen=true;
        exception=assertThrows(Exception.class,()-> account.deposit(50.0));
        assertTrue(exception.getMessage().contains("Account is frozen"),"Should throw exception for depositing to a frozen account");
    }

    /**
     * Test case for the transfer method in the BankAccount class.
     * It checks if the transfer is processed correctly under different scenarios:
     * - Valid transfer amount
     * - Transfer amount exceeding account balance
     * - Negative transfer amount
     * - Attempting transfer from a frozen account
     *
     * @throws Exception if an error occurs during the transfer process
     */
    @Test
    public void testTransfer() throws Exception {
        BankAccount account1 = new BankAccount(12345678,400); 
        BankAccount account2 = new BankAccount(87654321, 100);
       
        // Testing valid transfer
        account1.transfer(account2, 100.0);
        assertEquals(300.0, account1.getBalance(), "After transferring 100.0 from account1, balance should be 300.0");
        assertEquals(200.0, account2.getBalance(), "After receiving 100.0 to account2, balance should be 200.0");

        // Testing transfer amount greater than balance
        Exception exception = assertThrows(IllegalArgumentException.class,()-> account1.transfer(account2,700.0));
        assertTrue(exception.getMessage().contains("Amount enter is greater than balance")," Should throw exception for insufficient funds");

        // Testing negative transfer amount
        exception = assertThrows(IllegalArgumentException.class, () -> account1.transfer(account2, -50.0));
        assertTrue(exception.getMessage().contains("Cannot transfer negative amount"),
                "Should throw exception for negative transfer amount");

        // Testing transfer from a frozen account
        account1.isFrozen = true;
        exception = assertThrows(Exception.class, () -> account1.transfer(account2, 50.0));
        assertTrue(exception.getMessage().contains("Account is frozen"),
                "Should throw exception for transferring from a frozen account");
    }
}
