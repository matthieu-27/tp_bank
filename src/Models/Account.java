package Models;

public class Account {
    public String accountNumber;
    public double balance;
    public int bankId;

    public Account(String accountNumber, double balance, int bankId){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "[" + this.accountNumber + "]: " + this.balance + "€";
    }
}