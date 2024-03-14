package edu.ithaca.dturnbull.bank;

public class printedListsExamples {
    public static void main(String[] args) {
        
        CentralBank bank = new CentralBank();
        // Creating instances
        Admin admin1 = new Admin("admin1@example.com", "adminPassword", bank);
        BankTeller teller1 = new BankTeller("teller1@example.com", "tellerPassword", bank);
        User user1 = new User("user1@example.com", "userPassword", bank);

        // Adding instances to the lists
        bank.addAdmin(admin1);
        bank.addBankTeller(teller1);
        bank.addUser(user1);
        
        bank.printAdminListDetails();
        bank.printTellerListDetails();
        bank.printUserListDetails();
        bank.getOverallAmount();

        


    }
}
