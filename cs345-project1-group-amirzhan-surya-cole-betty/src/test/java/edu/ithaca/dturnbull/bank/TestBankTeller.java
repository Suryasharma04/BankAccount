package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BankTeller functionalities.
 */
public class TestBankTeller {

    /**
     * Helper method to create a test bank with admins, tellers, and users.
     * 
     * @return CentralBank instance representing the test bank
     */
    CentralBank createTestBank() {
        CentralBank testBank = new CentralBank();

        // Creating test admins
        Admin testAdmin0 = new Admin("testadmin0@gmail.com", "password0", testBank);
        Admin testAdmin1 = new Admin("testadmin1@gmail.com", "password1", testBank);
        testBank.getAdminList().add(testAdmin0);
        testBank.getAdminList().add(testAdmin1);

        // Creating test tellers
        BankTeller testTeller0 = new BankTeller("testteller0@gmail.com", "password2", testBank);
        BankTeller testTeller1 = new BankTeller("testteller1@gmail.com", "password3", testBank);
        testBank.getTellerList().add(testTeller0);
        testBank.getTellerList().add(testTeller1);

        // Creating test users
        User testUser0 = new User("testuser0@gmail.com", "password4", testBank);
        User testUser1 = new User("testuser1@gmail.com", "password5", testBank);
        User testUser2 = new User("testuser2@gmail.com", "password6", testBank);
        testBank.getUserList().add(testUser0);
        testBank.getUserList().add(testUser1);
        testBank.getUserList().add(testUser2);

        return testBank;
    }

    // Creating a test bank
    CentralBank testBank = createTestBank();
    
    // BankTeller object for testing
    private BankTeller bankTeller;

    /**
     * Setup method to initialize the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        CentralBank newBankAccount = new CentralBank();

        // Creating a bank teller
        bankTeller = new BankTeller("teller@example.com", "password", testBank);
        
        // Adding bank accounts for the teller
        bankTeller.getBankAccountList().add(new BankAccount(12345678, 1000.0));
        bankTeller.getBankAccountList().add(new BankAccount(87654321, 2000.0));
        
        // Adding the teller to the test bank's user list
        newBankAccount.getUserList().add(bankTeller);
    }
    
    /**
     * Test case for closing an account.
     */
    @Test
    void testCloseAccount() {
        // Test closing an existing account
        BankAccount account = new BankAccount(12345678, 1000.0);
        bankTeller.closeAccount(account);
        assertFalse(bankTeller.getBankAccountList().contains(account));
        
        // Test closing a non-existent account
        BankAccount nonExistentAccount = new BankAccount(67534786, 2000.0);
        assertFalse(bankTeller.getBankAccountList().contains(nonExistentAccount));
    }

    /**
     * Test case for opening an account.
     */
    @Test
    void testOpenAccount() {
        // Test opening an account with a non-zero initial balance
        bankTeller.openAccount(1500.0);
        assertEquals(3, bankTeller.getBankAccountList().size());
        assertNotNull(bankTeller.getBankAccountList().get(2));
        
        // Test opening an account with a zero initial balance
        bankTeller.openAccount(0.0);
        assertEquals(4, bankTeller.getBankAccountList().size());
        assertNotNull(bankTeller.getBankAccountList().get(3));
    }
}
