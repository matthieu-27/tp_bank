package Business;

import Daos.AccountDao;
import Models.Account;

public class Bank {
	
	public static void deposit(double amount, Account account) throws Exception {
		AccountDao accountDao = new AccountDao();
        if(amount <= 0 ) {
            throw new InvalidAmountException("Amount cannot be zero or negative");
        } 
        try {
            accountDao.update(account);
        } catch (Exception e) {
            IO.println(e.getMessage());
        } finally {
        	accountDao.close();
        }
	}

}
