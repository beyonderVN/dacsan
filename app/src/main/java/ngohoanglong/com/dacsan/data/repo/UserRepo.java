package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.request.LoginRequest;
import ngohoanglong.com.dacsan.data.request.LogoutRequest;
import ngohoanglong.com.dacsan.data.request.SignupRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;
import ngohoanglong.com.dacsan.data.response.SignupResponse;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public interface UserRepo {
    Observable<LoginResponse> login(LoginRequest loginRequest);
    Observable<LogoutResponse> logout(LogoutRequest logoutRequest);

    Observable<SignupResponse> signup(SignupRequest signupRequest);
}
