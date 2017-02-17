package ngohoanglong.com.dacsan;

import android.app.Application;
import android.content.Context;

import ngohoanglong.com.dacsan.manager.AuthManager;

/**
 * Created by Long on 2/10/2017.
 */

public class DacsanApplication extends Application {
    private static final String TAG = "DacsanApplication";
    private static Context mContext;
    public static AuthManager authManager ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        authManager = new AuthManager(mContext);
    }

    public static Context getAppContext() {
        return mContext;
    }
}
