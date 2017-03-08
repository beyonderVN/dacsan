package ngohoanglong.com.dacsan.utils.recyclerview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

public final class StatefulRecyclerView
        extends RecyclerView {
    private static final String TAG = "StatefulRecyclerView";

    private int mScrollPosition;

    public StatefulRecyclerView(Context context) {
        super(context);
    }

    public StatefulRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatefulRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager != null && layoutManager instanceof LinearLayoutManager){
            mScrollPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }else if(layoutManager != null && layoutManager instanceof StaggeredGridLayoutManager){
            int[] i  = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            mScrollPosition = getLastVisibleItem(i);
        }
        SavedState newState = new SavedState(superState);
        newState.mScrollPosition = mScrollPosition;
        Log.d(TAG, "onSaveInstanceState: "+mScrollPosition);
        return newState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if(state != null && state instanceof SavedState){
            mScrollPosition = ((SavedState) state).mScrollPosition;
            Log.d(TAG, "onRestoreInstanceState: "+mScrollPosition);
            LayoutManager layoutManager = getLayoutManager();
            if(layoutManager != null){
                Log.d(TAG, "onRestoreInstanceState3: "+mScrollPosition);
                int count = layoutManager.getItemCount();
                Log.d(TAG, "onRestoreInstanceState: count: "+count);
                if(mScrollPosition != RecyclerView.NO_POSITION && mScrollPosition < count){
                    Log.d(TAG, "onRestoreInstanceState3: "+mScrollPosition);
                    layoutManager.scrollToPosition(mScrollPosition);
                }
            }
        }
    }
    public void restoreInstanceState(){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager != null){
            int count = layoutManager.getItemCount();
            if(mScrollPosition != RecyclerView.NO_POSITION && mScrollPosition < count){
                Log.d(TAG, "onRestoreInstanceState4: "+mScrollPosition);
                layoutManager.scrollToPosition(mScrollPosition);
            }
        }
    }
    static class SavedState extends android.view.View.BaseSavedState {
        public int mScrollPosition;
        SavedState(Parcel in) {
            super(in);
            mScrollPosition = in.readInt();
        }
        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(mScrollPosition);
        }
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }
}