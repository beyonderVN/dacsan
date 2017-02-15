package ngohoanglong.com.dacsan;

import android.app.Application;
import android.content.Context;

import java.text.NumberFormat;
import java.util.Locale;

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
        double amount =200.0;
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(currencyFormatter.format(amount));
    }

    public static Context getAppContext() {
        return mContext;
    }
}
