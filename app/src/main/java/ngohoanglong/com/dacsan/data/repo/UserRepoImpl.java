package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.request.LoginRequest;
import ngohoanglong.com.dacsan.data.request.LogoutRequest;
import ngohoanglong.com.dacsan.data.request.SignupRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;
import ngohoanglong.com.dacsan.data.response.SignupResponse;
import ngohoanglong.com.dacsan.model.User;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public class UserRepoImpl implements UserRepo {
    @Override
    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        if (loginRequest.getUsername().equals("long@gmail.com")&&loginRequest.getPassword().equals("123456")){
            return Observable.create(subscriber -> {
                subscriber.onNext(new LoginResponse(true, new User("long@gmail.com","","1")));
            });

        }
        return Observable.create(subscriber -> {
            subscriber.onNext(new LoginResponse(false, new User()));
        });

    }

    @Override
    public Observable<LogoutResponse> logout(LogoutRequest logoutRequest) {
        return Observable.create(subscriber -> {
            subscriber.onNext(new LogoutResponse());
        });
    }

    @Override
    public Observable<SignupResponse> signup(SignupRequest signupRequest) {
        if (signupRequest.getUsername().equals("long@gmail.com")&&signupRequest.getPassword().equals("123456")){
            return Observable.create(subscriber -> {
                subscriber.onNext(new SignupResponse(true, new User("long@gmail.com","","1")));
            });

        }
        return Observable.create(subscriber -> {
            subscriber.onNext(new SignupResponse(false, new User()));
        });
    }
}
