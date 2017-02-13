package ngohoanglong.com.dacsan;

import android.app.Application;
import android.content.Context;

/**
 * Created by Long on 2/10/2017.
 */

public class DacsanApplication extends Application {
    private static final String TAG = "DacsanApplication";
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
