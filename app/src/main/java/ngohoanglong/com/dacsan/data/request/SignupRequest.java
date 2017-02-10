package ngohoanglong.com.dacsan.data.request;

/**
 * Created by Long on 2/10/2017.
 */
public class SignupRequest {
    private final String username;
    private final String password;

    public SignupRequest(String username, String password) {
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
