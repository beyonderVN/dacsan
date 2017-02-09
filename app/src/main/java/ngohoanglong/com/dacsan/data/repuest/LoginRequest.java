package ngohoanglong.com.dacsan.data.repuest;

/**
 * Created by Long on 2/8/2017.
 */

public class LoginRequest {
    private final String username;
    private final String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
