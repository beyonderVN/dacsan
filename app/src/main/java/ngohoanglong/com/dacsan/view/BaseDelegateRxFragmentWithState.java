package ngohoanglong.com.dacsan.view;

import android.support.annotation.NonNull;

import ngohoanglong.com.dacsan.view.delegate.StateDelegate;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseDelegateRxFragmentWithState<M extends BaseStateViewModel,S extends BaseState> extends BaseDelegateRxFragment {
    private StateDelegate<M,S> stateDelegate = new StateDelegate<M,S>() {
        @NonNull
        @Override protected M createViewModel() {
            return BaseDelegateRxFragmentWithState.this.createViewModel();
        }

        @NonNull @Override protected S createStateModel() {
            return BaseDelegateRxFragmentWithState.this.createPresentationModel();
        }
    };
    {
        lifecycleDelegates.add(stateDelegate);
    }

    @NonNull protected abstract M createViewModel();


    @NonNull protected abstract S createPresentationModel();

    public M getViewModel() {
        return stateDelegate.getViewModel();
    }
}
