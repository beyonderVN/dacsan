package ngohoanglong.com.dacsan;

import android.app.Application;
import android.content.Context;

import ngohoanglong.com.dacsan.dependencyinjection.component.AppComponent;
import ngohoanglong.com.dacsan.dependencyinjection.component.DaggerAppComponent;
import ngohoanglong.com.dacsan.dependencyinjection.module.AppModule;
import ngohoanglong.com.dacsan.manager.AuthManager;

/**
 * Created by Long on 2/10/2017.
 */

public class DacsanApplication extends Application {
    private static final String TAG = "DacsanApplication";
    private static Context mContext;
    public static AuthManager authManager ;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        authManager = new AuthManager(mContext);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
