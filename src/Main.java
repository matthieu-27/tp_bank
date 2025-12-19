import Business.InvalidAmountException;
import Daos.AccountDao;
import Daos.CustomerDao;
import Models.Account;
import Models.Customer;

/**
 * Classe principale démontrant l'utilisation de l'architecture DAO générique
 */
public class Main {
    
    static void main(String[] args) {
        System.out.println("=== Démonstration de l'architecture DAO générique ===");
        
        try {
            // Démonstration avec AccountDao
            System.out.println("\n--- Test AccountDao ---");
            testAccountDao();
            
            // Démonstration avec CustomerDao
            System.out.println("\n--- Test CustomerDao ---");
            testCustomerDao();
            
        } catch (Exception e) {
            System.err.println("Erreur dans l'application: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Méthode de test pour AccountDao
     */
    private static void testAccountDao() throws Exception {
        AccountDao accountDao = new AccountDao();
        
        try {
            // Créer un nouveau compte
            Account newAccount = new Account("PT2112345678901234567890124", 1000.0, 1);
            Account createdAccount = accountDao.create(newAccount);
            System.out.println("Compte créé: " + createdAccount);
            
            // Lire le compte créé
            String number = "PT2112345678901234567890124";
            if (accountDao.exists(number)) {
                Account retrievedAccount = accountDao.read(number).get();
                System.out.println("Compte récupéré: " + retrievedAccount);
            }
            
            // Mettre à jour le compte
            if (accountDao.exists(number)) {
                Account accountToUpdate = accountDao.read(number).get();
                accountToUpdate.balance = 1500.0;
                Account updatedAccount = accountDao.update(accountToUpdate);
                System.out.println("Compte mis à jour: " + updatedAccount);
            }
            
            // Test fonction deposit()
            if(accountDao.exists(number)){
                Account retrievedAccount = accountDao.read(number).get();
                try {
                    retrievedAccount.deposit(-100);
                } catch (InvalidAmountException e) {
                    IO.println(e.getMessage());
                }
                retrievedAccount.deposit(10000);
            }
            
            // Lire tous les comptes
            System.out.println("Tous les comptes:");
            for (Account account : accountDao.readAll()) {
                System.out.println("  " + account);
            }
            
            // Supprimer le compte (nettoyage)
            if (accountDao.exists(number)) {
                boolean deleted = accountDao.delete(number);
                System.out.println("Compte supprimé: " + deleted);
            }
            
        } finally {
            accountDao.close();
        }
    }
    
    /**
     * Méthode de test pour CustomerDao
     */
    private static void testCustomerDao() throws Exception {
        CustomerDao customerDao = new CustomerDao();
        
        try {
            // Créer un nouveau client
            Customer newCustomer = new Customer( "Jean", "12 Rue des Gens");
            Customer createdCustomer = customerDao.create(newCustomer);
            System.out.println("Client créé: " + createdCustomer);
            
            // Lire le client créé
            int customerId = createdCustomer.id;
            if (customerDao.exists(customerId)) {
                Customer retrievedCustomer = customerDao.read(customerId).get();
                System.out.println("Client récupéré: " + retrievedCustomer);
            }
            
            // Mettre à jour le client
            if (customerDao.exists(customerId)) {
                Customer customerToUpdate = customerDao.read(customerId).get();
                customerToUpdate.address = "1 Rue des Champs Elysées";
                Customer updatedCustomer = customerDao.update(customerToUpdate);
                System.out.println("Client mis à jour: " + updatedCustomer);
            }
            
            // Lire tous les clients
            System.out.println("Tous les clients:");
            for (Customer customer : customerDao.readAll()) {
                System.out.println("  " + customer);
            }
            
            // Supprimer le client (nettoyage)
            if (customerDao.exists(customerId)) {
                boolean deleted = customerDao.delete(customerId);
                System.out.println("Client supprimé: " + deleted);
            }
            
        } finally {
            customerDao.close();
        }
    }
}