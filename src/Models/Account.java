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

}