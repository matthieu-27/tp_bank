package Business;

import java.sql.SQLException;

public class BankExceptions extends SQLException {
    public BankExceptions(String m) {
        super(m);
    }
}

