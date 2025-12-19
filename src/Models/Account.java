package Models;

import Business.InvalidAmountException;
import Daos.AccountDao;

public class Account {
    public String number;
    public double balance;
    public int bankId;

    public Account(String accountNumber, double balance, int bankId) {
        this.number = accountNumber;
        this.balance = balance;
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "[" + this.number + "]: " + this.balance + "€";
    }

    public void deposit(double amount) throws InvalidAmountException{
        if(amount <= 0 ) {
            throw new InvalidAmountException("Amount cannot be zero or negative");
        }
        this.balance += amount;
        try {
            var dao = new AccountDao();
            dao.update(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}