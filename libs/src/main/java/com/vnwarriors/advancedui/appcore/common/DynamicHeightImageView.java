package com.vnwarriors.advancedui.appcore.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by yuvaraj on 3/4/16.
 */
public class DynamicHeightImageView extends ImageView {
    private static final String TAG = "DynamicHeightImageView";
    private double whRatio = 0;

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context);
    }


    WeakReference<SetImageRatio> setImageRatioWeakReference;
    public void setRatio(double ratio,SetImageRatio setImageRatio) {
        whRatio = ratio;
        Log.d(TAG, "setRatio: "+whRatio);
        setImageRatioWeakReference = new WeakReference<>(setImageRatio);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        if (whRatio != 0) {
            int width = getMeasuredWidth();
            int height = (int) (whRatio * width);
            setMeasuredDimension(width, height);
//			Log.d(TAG, "onMeasure: "+whRatio);
        } else {
            if (!(getDrawable() == null)) {
                int width = getDrawable().getIntrinsicWidth();
                int height = getDrawable().getIntrinsicHeight();
//                Log.d(TAG, "onMeasure: "+width+"/"+height);
                if (height > 0 && width > 0) {
                    SetImageRatio setImageRatio = setImageRatioWeakReference.get();
                    if(setImageRatio!=null ){
                        setImageRatio.setImageRatio((double) height / (double) width);
                    }
                    whRatio = (double) height / (double) width;
                    setMeasuredDimension(getMeasuredWidth(), (int) (whRatio * getMeasuredWidth()));
                }
            }else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }


    }
    public interface SetImageRatio {
        void setImageRatio(double ratio);
    }

}
