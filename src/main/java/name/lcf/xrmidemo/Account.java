package name.lcf.xrmidemo;

import java.io.Serializable;

public class Account implements Serializable {

    private String username;
    private String fullName;
    private String email;

    public Account(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }
}
