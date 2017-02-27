package ngohoanglong.com.dacsan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.utils.LifecycleDelegate;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseDelegateRxActivity extends BaseRxActivity {
    protected List<LifecycleDelegate> lifecycleDelegates = new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onCreate(savedInstanceState);
        }
    }
    public CompositeSubscription getCompositeSubscription(){
        return compositeSubscription;
    }

    protected abstract void bindViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStart();
        }

    }

    @Override
    protected void onStop() {
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
    }
}
