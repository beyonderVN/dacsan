package ngohoanglong.com.dacsan.view.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ViewAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.data.response.LoginResponse;
import ngohoanglong.com.dacsan.databinding.ActivityLoginBinding;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import ngohoanglong.com.dacsan.view.BaseActivity;
import ngohoanglong.com.dacsan.view.main.MainActivity;
import ngohoanglong.com.dacsan.view.signup.SignupActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    LoginViewModel viewModel ;
    @BindView(R.id.vaStateController)
    ViewAnimator viewAnimator;
    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        Log.d(TAG, "onLoginClick: ");
        viewModel.login()
                .takeUntil(stopEvent())
                .subscribe(loginResponse -> handleResponse(loginResponse))
        ;
    }
    private void handleResponse(LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
//            startActivity(MainActivity.getIntentNewTask(this));
        }
    }
    @OnClick(R.id.btn_signup)
    public void onSighupClick() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @OnClick(R.id.btnResetPassword)
    public void onResetPasswordClick() {
//        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DacsanApplication.authManager.isLogin()
                .takeUntil(stopEvent())
                .subscribe(aBoolean -> handleResponse(aBoolean));
        super.onCreate(savedInstanceState);
        viewModel = new LoginViewModel(new ThreadSchedulerImpl(AndroidSchedulers.mainThread(), Schedulers.io()),this.getApplicationContext().getResources());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void bindViewModel() {
        viewModel.toast()
                .takeUntil(stopEvent())
                .subscribe(this::showMessage);
        viewModel.loadingState()
                .takeUntil(stopEvent())
                .startWith(1)
                .subscribe(this::setLoadingState);
//        viewModel.loginIsSuccess()
//                .takeUntil(stopEvent())
//                .subscribe(aBoolean -> {
//                    handleResponse(aBoolean);
//                });
    }

    private void showMessage(String value) {
        new AlertDialog.Builder(this)
                .setMessage(value)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void setLoadingState(Integer value) {
        viewAnimator.setDisplayedChild(value);
    }

    private void handleResponse(Boolean aBoolean){
        if(aBoolean){
//            new AlertDialog.Builder(this)
//                    .setMessage("Login Successfully!")
//                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
//                        dialog.dismiss();
//                        startActivity(MainActivity.getIntentNewTask(LoginActivity.this));
//                    })
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
            startActivity(MainActivity.getIntentNewTask(LoginActivity.this));
        }
    }

    public static Intent getIntentNewTask(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
