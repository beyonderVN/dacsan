package ngohoanglong.com.dacsan.view;

import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseRxDelegateFragment extends BaseDelegateFragment {
    private RxDelegate rxDelegate = new RxDelegate() {
        @Override
        protected void bindViewModel() {
            BaseRxDelegateFragment.this.bindViewModel();
        }
    };
    {
        lifecycleDelegates.add(rxDelegate);
    }
    public abstract void bindViewModel();
    public Observable<Integer> stopEvent() {
        return rxDelegate.stopEvent();
    }
    public Observable<Integer> startEvent() {
        return rxDelegate.startEvent();
    }
    public CompositeSubscription getCompositeSubscription(){
        return rxDelegate.getCompositeSubscription();
    }
}
