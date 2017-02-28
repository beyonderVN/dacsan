package ngohoanglong.com.dacsan.view.login;

import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.data.repo.UserRepo;
import ngohoanglong.com.dacsan.data.request.LoginRequest;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.rxview.TextChange;
import ngohoanglong.com.dacsan.view.BaseViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by Long on 12/1/2016.
 */
@ActivityScope
public class LoginViewModel extends BaseViewModel {
    private static final String TAG = "LoginViewModel";
    private LoginValidator validator = new LoginValidator();
    private UserRepo userRepo;

    private String email = "";
    private String password = "";
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableBoolean loginBtnState = new ObservableBoolean(false);
    private BehaviorSubject<String> toast = BehaviorSubject.create();
    private PublishSubject<Integer> loadingState = PublishSubject.create();

    @Inject
    public LoginViewModel(@NonNull ThreadScheduler threadScheduler,
                          @NonNull Resources resources,
                          @NonNull UserRepo userRepo
    ) {
        super(threadScheduler, resources);
        this.userRepo = userRepo;
    }

    @Override
    public void bindViewModel() {

    }

    public Observable<String> toast() {
        return toast.asObservable();
    }

    public Observable<Integer> loadingState() {
        return loadingState.asObservable();
    }

    public Observable<Boolean> loginIsSuccess() {
        return DacsanApplication.authManager.isLogin()
                .compose(withScheduler())
                .doOnNext(aBoolean -> Log.d(TAG, "loginIsSuccess: "+aBoolean));
    }

    public TextChange emailChange = value -> {
        email = value;
        validateEmail();
    };

    public TextChange passwordChange = value -> {
        password = value;
        validatePassword();
    };

    private void validatePassword() {
        passwordError.set(null);

        if (!validator.validatePassword(password)) {
            passwordError.set("Password is too short");
        }
        updateBtnState();
    }

    private void validateEmail() {
        emailError.set(null);
        if (!validator.validateEmail(email)) {
            emailError.set("Invalid email");
        }
        updateBtnState();
    }

    private void updateBtnState() {
        loginBtnState.set(!hasEmptyData() && !hasError());
    }

    private boolean hasEmptyData() {
        return email.equals("")
                || password.equals("");
    }

    private boolean hasError() {
        return emailError.get() != null
                || passwordError.get() != null;
    }

    public Observable<LoginResponse> login() {
        return userRepo
                .login(new LoginRequest(email, password))
                .compose(withScheduler())
                .doOnSubscribe(() -> loadingState.onNext(0))
                .doOnNext(loginResponse -> handleResponse(loginResponse))
                ;
    }

    private void handleResponse(LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
            DacsanApplication.authManager.login(loginResponse.getUser());
            Log.d(TAG, "handleResponse: authentication.isSuccess()");
        } else {
            loadingState.onNext(1);
            toast.onNext("Failed!");
        }
    }

}
