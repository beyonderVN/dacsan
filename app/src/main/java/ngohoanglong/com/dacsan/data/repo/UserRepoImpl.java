package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.repuest.LoginRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;
import ngohoanglong.com.dacsan.model.User;

/**
 * Created by Long on 2/8/2017.
 */

public class UserRepoImpl implements UserRepo {
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.getUsername().equals("long")&&loginRequest.getPassword().equals("123456")){
            return new LoginResponse(true, new User("long","","1"));
        }
        return new LoginResponse(false, new User());
    }

    @Override
    public LogoutResponse loout(LogoutResponse logoutResponse) {
        return null;
    }
}
