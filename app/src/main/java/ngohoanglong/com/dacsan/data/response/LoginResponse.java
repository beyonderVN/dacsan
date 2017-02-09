package ngohoanglong.com.dacsan.data.response;

import ngohoanglong.com.dacsan.model.User;

/**
 * Created by Long on 2/8/2017.
 */

public class LoginResponse {
    private boolean success;
    private User user;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
