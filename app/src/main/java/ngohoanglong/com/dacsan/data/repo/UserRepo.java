package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.repuest.LoginRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;

/**
 * Created by Long on 2/8/2017.
 */

public interface UserRepo {
    LoginResponse login(LoginRequest loginRequest);
    LogoutResponse loout(LogoutResponse logoutResponse);
}
