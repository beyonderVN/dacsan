package ngohoanglong.com.dacsan.view;

import android.support.annotation.NonNull;
import android.util.Log;

import ngohoanglong.com.dacsan.view.delegate.StateDelegate;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseRxActivityWithState<M extends BaseStateViewModel,S extends BaseState> extends BaseDelegateRxActivity {
    private static final String TAG = "BaseRxActivityWithState";
    private M viewModel;
    private StateDelegate<M,S> stateDelegate = new StateDelegate<M,S>() {

        @NonNull
        @Override
        protected M createViewModel() {
            return BaseRxActivityWithState.this.createViewModel();
        }

        @NonNull @Override protected S createStateModel() {
            Log.d(TAG, "createStateModel: ");
            return BaseRxActivityWithState.this.createPresentationModel();
        }
    };
    {
        lifecycleDelegates.add(stateDelegate);
    }

    @NonNull protected abstract M createViewModel();

    /**
     * Used to create the Presentation Model that will be attached to your presenter in #onAttach()
     * method of your presenter.
     *
     * NOTE: this will be called only if there is no Presentation Model persisted in your
     * savedInstanceState!
     *
     * You can retrieve the arguments from your Intent's extra and pass it
     * to your Presentation's model constructor.
     * @return Presentation Model instance used by your Presenter.
     */
    @NonNull protected abstract S createPresentationModel();
}
