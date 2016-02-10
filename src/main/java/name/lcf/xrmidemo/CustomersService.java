package name.lcf.xrmidemo;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class CustomersService {

    /**
     * The database of users previously registered in the system
     */
    private Map<String, Account> accounts = new HashMap<String, Account>() {{
        put("john", new Account("john", "John Smith", "john@example.com"));
    }};

    public Account findCustomerAccount(String username) {
        return accounts.get(username);
    }
}
