package ngohoanglong.com.dacsan.utils;

import android.content.Context;
import android.util.AttributeSet;

import com.tuyenmonkey.mkloader.MKLoader;

/**
 * Created by Long on 3/3/2017.
 */

public class MMKLoader extends MKLoader {
    public MMKLoader(Context context) {
        super(context);
    }
    public MMKLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMKLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAnimation().start();
    }

    @Override
    protected void onDetachedFromWindow() {

         getAnimation().cancel();

        super.onDetachedFromWindow();

    }
}
