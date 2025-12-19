package Business;

import Daos.AccountDao;
import Models.Account;

public class Bank {
	
	public static void deposit(double amount, Account account) throws Exception {
		AccountDao accountDao = new AccountDao();
        if(account == null) throw new AccountNotFoundException("Account not found");
        if(amount <= 0 ) {
            throw new InvalidAmountException("Amount cannot be zero or negative");
        } 
        try {
            account.balance += amount;
            accountDao.update(account);
        } catch (Exception e) {
            IO.println(e.getMessage());
        } finally {
        	accountDao.close();
        }
	}

    public static void withdraw(double amount, Account account) throws Exception {
        AccountDao accountDao = new AccountDao();
        if(account == null) throw new AccountNotFoundException("Account not found");
        if(account.balance < amount){
            throw new InsufficientBalanceException("Sold balance is insufficient.");
        }
        try{
            account.balance -= amount;
            accountDao.update(account);
        } catch (Exception e) {
            IO.println(e.getMessage());
        }
    }
}
