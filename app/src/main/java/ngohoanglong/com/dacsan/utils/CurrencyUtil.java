package ngohoanglong.com.dacsan.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Long on 2/15/2017.
 */

public class CurrencyUtil {
    public static String convertCurrency(double value, Locale locale){
        String result = "";
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        result = currencyFormatter.format(value);
        return result;
    }
}
