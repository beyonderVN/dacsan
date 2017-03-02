package ngohoanglong.com.dacsan;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    public static AuthManager authManager;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        authManager = appComponent.getAuthManager();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        DacsanApplication application = (DacsanApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    public static Context getAppContext() {
        return mContext;
    }

}
