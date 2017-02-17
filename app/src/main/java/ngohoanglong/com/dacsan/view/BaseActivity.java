package ngohoanglong.com.dacsan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.utils.LifecycleDelegate;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by nongdenchet on 11/26/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
//    private AppComponent appComponent;
    private static final int START = 0;
    private static final int STOP = 1;
    private PublishSubject<Integer> stopEvent = PublishSubject.create();
    private PublishSubject<Integer> startEvent = PublishSubject.create();
    public Observable<Integer> stopEvent() {
        return stopEvent.asObservable();
    }
    public Observable<Integer> startEvent() {
        return startEvent.asObservable();
    }
    protected List<LifecycleDelegate> lifecycleDelegates = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState,int layout) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onCreate(savedInstanceState);
        }
//        appComponent = ((MyApplication) getApplication()).component();
    }
//    protected AppComponent getAppComponent() {
//        return appComponent;
//    }

    protected abstract void bindViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStart();
        }
        startEvent.onNext(START);
        bindViewModel();
    }

    @Override
    protected void onStop() {
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
        stopEvent.onNext(STOP);
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
        super.onDestroy();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onStop();
        }
    }
}
