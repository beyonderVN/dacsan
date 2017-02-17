package ngohoanglong.com.dacsan.view.delegate;

import android.content.Intent;
import android.os.Bundle;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.utils.BaseDelegate;
import ngohoanglong.com.dacsan.view.BaseActivity;
import ngohoanglong.com.dacsan.view.login.LoginActivity;

/**
 * Created by Long on 11/15/2016.
 */

public class AuthDelegate extends BaseDelegate {

    BaseActivity activity;

    public AuthDelegate(BaseActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle bundle) {
        DacsanApplication.authManager.isLogin()
                .takeUntil(activity.stopEvent())
                .subscribe(aBoolean -> {
                    if(!aBoolean){
                        Intent intent = LoginActivity.getIntentNewTask(activity);
                        activity.startActivity(intent);
                    }
                });
    }

}
