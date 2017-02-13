package ngohoanglong.com.dacsan.view.signup;

import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import ngohoanglong.com.dacsan.data.repo.UserRepo;
import ngohoanglong.com.dacsan.data.repo.UserRepoImpl;
import ngohoanglong.com.dacsan.data.request.SignupRequest;
import ngohoanglong.com.dacsan.data.response.SignupResponse;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.rxview.TextChange;
import ngohoanglong.com.dacsan.view.BaseViewModel;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Long on 12/5/2016.
 */

public class SignupViewModel extends BaseViewModel{
    private static final String TAG = "SignupViewModel";
    private SignupValidator validator = new SignupValidator();
    private UserRepo repo ;
    ;
    private String email = "";
    private String password = "";
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableBoolean signupBtnState = new ObservableBoolean(false);
    private PublishSubject<String> toast = PublishSubject.create();
    private PublishSubject<Integer> loadingState = PublishSubject.create();

    public SignupViewModel(@NonNull ThreadScheduler threadScheduler, @NonNull Resources resources) {
        super(threadScheduler, resources);
    }

    public Observable<String> message() {
        return toast.asObservable();
    }

    public Observable<Integer> loadingState() {
        return loadingState.asObservable();
    }
    public void init() {
        repo = new UserRepoImpl();
        loadingState.onNext(1);
    }
    public TextChange emailSignupChange = value -> {
        email = value;
        validateEmail();
    };

    public TextChange passwordSignupChange = value -> {
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
        signupBtnState.set(!hasEmptyData() && !hasError());
    }

    private boolean hasEmptyData() {
        return email.equals("")
                || password.equals("");
    }

    private boolean hasError() {
        return emailError.get() != null
                || passwordError.get() != null;
    }


    @NonNull
    public  Observable<SignupResponse> signup() {
        return repo.signup(new SignupRequest(email,password))
                .compose(withScheduler())
                .doOnSubscribe(() ->  loadingState.onNext(0));
    }

    private static String convertEmailToName(String email) {
        String[] s = email.split("@");
        return s.length > 0 ? s[0] : email;
    }
    private void handleResponse(SignupResponse signupResponse) {
        if (signupResponse.isSuccess()) {
//            toast.onNext("Login Successfully!");
            Log.d(TAG, "handleResponse: authentication.isSuccess()");
        } else {
            loadingState.onNext(1);
            toast.onNext("Failed!");
        }
    }
}
