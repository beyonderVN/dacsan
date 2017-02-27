package ngohoanglong.com.dacsan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by nongdenchet on 11/26/16.
 */

public abstract class BaseRxActivity extends AppCompatActivity {
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
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    protected void onCreate(@Nullable Bundle savedInstanceState,int layout) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
    }
    public CompositeSubscription getCompositeSubscription(){
        return compositeSubscription;
    }

    protected abstract void bindViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        startEvent.onNext(START);
        bindViewModel();
    }

    @Override
    protected void onStop() {
        stopEvent.onNext(STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeSubscription.clear();
        super.onDestroy();
    }


}
