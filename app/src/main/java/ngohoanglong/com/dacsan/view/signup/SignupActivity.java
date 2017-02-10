package ngohoanglong.com.dacsan.view.signup;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.ViewAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.data.response.SignupResponse;
import ngohoanglong.com.dacsan.databinding.ActivitySignupBinding;
import ngohoanglong.com.dacsan.view.BaseActivity;
import ngohoanglong.com.dacsan.view.login.LoginActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SignupActivity extends BaseActivity {
    private static final String TAG = "SignupActivity";
    private ActivitySignupBinding binding;
    SignupViewModel viewModel = new SignupViewModel();
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.vaLoginState)
    ViewAnimator viewAnimator;
    @OnClick(R.id.btnSignup)
    void signupClick(){
        viewModel.signup()
                .takeUntil(stopEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignupResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(SignupResponse signupResponse) {
                        Log.d(TAG, "onNext: ");
                        startActivity(LoginActivity.getIntentNewTask(SignupActivity.this));
                        finish();
                    }
                });
    }
    @OnClick(R.id.btnResetPassword)
    void resetPasswordClick(){
//        startActivity(new Intent(SignupActivity.this, .class));
    }
    @OnClick(R.id.btnSignin)
    void signinClick(){
        finishAfterTransition();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);
        setupUI();
        setLoadingState(1);
    }

    private void setupUI() {
        viewModel.init();
    }

    @Override
    protected void bindViewModel() {
        viewModel.message()
                .takeUntil(stopEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMessage);
        viewModel.loadingState()
                .takeUntil(stopEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setLoadingState);
    }


    private void showMessage(String value) {
        new AlertDialog.Builder(this)
                .setMessage(value)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void setLoadingState(Integer value) {
        Log.d(TAG, "setLoadingState: "+value);
        viewAnimator.setDisplayedChild(value);
    }
}
