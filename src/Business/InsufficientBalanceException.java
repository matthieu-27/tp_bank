package Business;

public class InsufficientBalanceException extends BankExceptions {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
