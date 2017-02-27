package ngohoanglong.com.dacsan.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Long on 2/13/2017.
 */

public abstract class BaseRxFragment extends Fragment {
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected abstract void bindViewModel();

    @Override
    public void onStart() {
        super.onStart();
        startEvent.onNext(START);
        bindViewModel();
    }

    @Override
    public void onStop() {
        stopEvent.onNext(STOP);
        super.onStop();
    }
}
