package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.repuest.LoginRequest;
import ngohoanglong.com.dacsan.data.repuest.LogoutRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public interface UserRepo {
    Observable<LoginResponse> login(LoginRequest loginRequest);
    Observable<LogoutResponse> logout(LogoutRequest logoutRequest);
}
