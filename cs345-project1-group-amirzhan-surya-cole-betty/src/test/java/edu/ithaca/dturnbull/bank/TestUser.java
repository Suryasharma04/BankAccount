package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestUser {

    CentralBank testBank = createTestBank();

    CentralBank createTestBank() {

        CentralBank testBank = new CentralBank();

        Admin testAdmin0 = new Admin("testadmin0@gmail.com", "password0", testBank);
        Admin testAdmin1 = new Admin("testadmin1@gmail.com", "password1", testBank);
        testBank.getAdminList().add(testAdmin0);
        testBank.getAdminList().add(testAdmin1);

        BankTeller testTeller0 = new BankTeller("testteller0@gmail.com", "password2", testBank);
        BankTeller testTeller1 = new BankTeller("testteller1@gmail.com", "password3", testBank);
        testBank.tellerList.add(testTeller0);
        testBank.tellerList.add(testTeller1);

        User testUser0 = new User("testuser0@gmail.com", "password4", testBank);
        User testUser1 = new User("testuser1@gmail.com", "password5", testBank);
        User testUser2 = new User("testuser2@gmail.com", "password6", testBank);
        testBank.userList.add(testUser0);
        testBank.userList.add(testUser1);
        testBank.userList.add(testUser2);

        return testBank;
    }

    @Test
    void isAdminTest() throws Exception{
        // Testing that isAdmin will return false if the user is a regular user
        assertFalse(testBank.getUserList().get(0).isAdmin());
        assertFalse(testBank.getUserList().get(1).isAdmin());
        assertFalse(testBank.getUserList().get(2).isAdmin());

        // Testing that isAdmin will return false if the user is a teller
        assertFalse(testBank.getTellerList().get(0).isAdmin());
        assertFalse(testBank.getTellerList().get(1).isAdmin());

        // Testing that isAdmin will return true if the user is an admin
        assertTrue(testBank.getAdminList().get(0).isAdmin());
        assertTrue(testBank.getAdminList().get(1).isAdmin());
    }

    @Test
    void isTellerTest() {
        // Testing that isTeller will return false if the user is a regular user
        assertFalse(testBank.getUserList().get(0).isTeller());
        assertFalse(testBank.getUserList().get(1).isTeller());
        assertFalse(testBank.getUserList().get(2).isTeller());

        // Testing that isTeller will return false if the user is an admin
        assertFalse(testBank.getAdminList().get(0).isTeller());
        assertFalse(testBank.getAdminList().get(1).isTeller());

        // Test that isTeller will return true if the user is a teller
        assertTrue(testBank.getTellerList().get(0).isTeller());
        assertTrue(testBank.getTellerList().get(1).isTeller());

    }
}
