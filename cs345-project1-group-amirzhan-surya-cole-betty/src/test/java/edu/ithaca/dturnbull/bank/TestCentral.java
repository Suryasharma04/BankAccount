package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test suite for the CentralBank class.
 */
public class TestCentral {

    /**
     * Helper method to create a test instance of CentralBank with predefined data.
     * @return CentralBank instance with predefined data.
     */
    CentralBank createTestBank() {
        CentralBank testBank = new CentralBank();

        // Create test admins
        Admin testAdmin0 = new Admin("testadmin0@gmail.com", "password0", testBank);
        Admin testAdmin1 = new Admin("testadmin1@gmail.com", "password1", testBank);
        testBank.getAdminList().add(testAdmin0);
        testBank.getAdminList().add(testAdmin1);

        // Create test tellers
        BankTeller testTeller0 = new BankTeller("testteller0@gmail.com", "password2", testBank);
        BankTeller testTeller1 = new BankTeller("testteller1@gmail.com", "password3", testBank);
        testBank.tellerList.add(testTeller0);
        testBank.tellerList.add(testTeller1);

        // Create test users
        User testUser0 = new User("testuser0@gmail.com", "password4", testBank);
        User testUser1 = new User("testuser1@gmail.com", "password5", testBank);
        User testUser2 = new User("testuser2@gmail.com", "password6", testBank);
        testBank.userList.add(testUser0);
        testBank.userList.add(testUser1);
        testBank.userList.add(testUser2);

        return testBank;
    }

    CentralBank testBank = createTestBank();

    /**
     * Test case to ensure CentralBank initializes with empty lists.
     */
    @Test
    public void testCentral(){
        CentralBank central = new CentralBank();
        assert(central.getUserList().size() == 0);
        assert(central.getAdminList().size() == 0);
        assert(central.getTellerList().size() == 0);
        assert(central.getTransactionHistory().size() == 0);
    }

    /**
     * Test case to verify the correctness of calculating the overall amount in the bank.
     */
    @Test
    public void testGetOverallAmount(){
        CentralBank central = new CentralBank();
        User user1 = new User("test@test.com", "password", testBank);
        User user2 = new User("test2@test.com", "password", testBank);
        BankAccount account1 = new BankAccount(12345612, 1000);
        BankAccount account2 = new BankAccount(12343613, 2000);
        BankAccount account3 = new BankAccount(12342614, 3000);
        user1.add(account1);
        user1.add(account2);
        central.getUserList().add(user1);
        assert(central.getOverallAmount() == 3000);
        user2.add(account3);
        central.getUserList().add(user2);
        assert(central.getOverallAmount() == 6000);
    }

    /**
     * Test case to confirm the email address existence within the CentralBank.
     */
    @Test
    public void testConfirmEmailAddress(){
        User user1 = new User("test@test.com", "password", testBank);
        CentralBank central = new CentralBank();
        central.getUserList().add(user1);
        assertTrue(central.confirmEmailAddress("test@test.com"));
        assertFalse(central.confirmEmailAddress("fake@test.com"));
    }

    /**
     * Test case to confirm the correctness of user password matching.
     */
    @Test
    public void testConfirmPassword(){
        User user1 = new User("test@test.com", "password", testBank);
        CentralBank central = new CentralBank();
        central.getUserList().add(user1);
        assertTrue(central.confirmPassword("test@test.com","password"));
        assertFalse(central.confirmPassword("fake@test.com","fake"));
    }

    /**
     * Test case to find a user based on email address and password.
     */
    @Test
    public void testFindUser(){
        User user2 = new User("testUser@user.com", "password", testBank);
        CentralBank central2 = new CentralBank();
        central2.getUserList().add(user2);
        assertTrue(central2.findUser("testUser@user.com", "password") == user2);
    }

    /**
     * Test case to find an account based on its unique identifier.
     */
    @Test 
    public void testFindAccountById(){
        User user3 = new User("testU1@user.com", "password", testBank);
        CentralBank central3 = new CentralBank();
        central3.getUserList().add(user3);
        BankAccount account1 = new BankAccount(12345612, 100);
        user3.add(account1);
        assertTrue(central3.findAccountById(12345612) == account1);
    }
}
