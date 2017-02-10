package ngohoanglong.com.dacsan.manager;

import ngohoanglong.com.dacsan.model.User;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Long on 2/10/2017.
 */

public class AuthManager {
    private User currentUser;
    private BehaviorSubject<Boolean> isLoginSuccess;

    public Observable<Boolean> isLogin(){
        return isLoginSuccess.asObservable();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
