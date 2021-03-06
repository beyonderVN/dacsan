package ngohoanglong.com.dacsan.view.welcome;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.view.BaseDelegateActivity;
import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import ngohoanglong.com.dacsan.view.login.LoginActivity;
import ngohoanglong.com.dacsan.view.main.MainActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseDelegateActivity {
    private static final String TAG = "WelcomeActivity";
    private RxDelegate rxDelegate = new RxDelegate();
    {
        lifecycleDelegates.add(rxDelegate);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DacsanApplication.getAppComponent()
                .inject(this);
        setContentView(R.layout.activity_welcome);
        DacsanApplication
                .getAppComponent()
                .getAuthManager()
                .isLogin()
                .takeUntil(rxDelegate.stopEvent())
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void handleResponse(Boolean aBoolean) {
            Log.d(TAG, "handleResponse: ");
            if (aBoolean) {
                startActivity(MainActivity.getIntentNewTask(WelcomeActivity.this));
            } else {
                startActivity(LoginActivity.getIntentNewTask(WelcomeActivity.this));
            }
    }

}
