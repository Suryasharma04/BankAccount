package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSaving {

    @Test
    public void testSavingAccountConstructor() {
        SavingAccount account = new SavingAccount(98978771, 100.0);
        assertEquals(98978771, account.getAccountID());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testCompoundInterest() {
        SavingAccount account = new SavingAccount(18787887, 100.0);
        account.compoundInterest();
        assertEquals(105.0, account.getBalance());
    }
}