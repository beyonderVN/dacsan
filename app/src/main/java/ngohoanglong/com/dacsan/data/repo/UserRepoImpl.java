package ngohoanglong.com.dacsan.data.repo;

import ngohoanglong.com.dacsan.data.repuest.LoginRequest;
import ngohoanglong.com.dacsan.data.repuest.LogoutRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.data.response.LogoutResponse;
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
}
