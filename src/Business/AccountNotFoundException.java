package Business;

public class AccountNotFoundException extends BankExceptions {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String m){
        super(m);
    }
}
