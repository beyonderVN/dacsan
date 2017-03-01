package ngohoanglong.com.dacsan.view.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

import ngohoanglong.com.dacsan.utils.BaseDelegate;
import ngohoanglong.com.dacsan.view.BaseState;
import ngohoanglong.com.dacsan.view.BaseStateViewModel;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class StateDelegate<M extends BaseStateViewModel,S extends BaseState> extends BaseDelegate {
    private static final String TAG = "StateDelegate";
    private M viewModel;
    private S state;
    private String presentationModelKey;
    private static final String EXTRA_VIEW_MODEL_STATE = "viewModelState";

    public void onCreate(@Nullable Bundle savedInstanceState) {
        presentationModelKey = EXTRA_VIEW_MODEL_STATE;
        state = restorePresentationModel(getClass(), savedInstanceState);
        if (state == null) {
            state = createStateModel();
        }
        this.viewModel = createViewModel();
        viewModel.setState(state);
    }

    /**
     * Used by the delegate to persist current Presentation Model due to configuration change.
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        state = (S) viewModel.saveInstanceState();
        outState.putSerializable(presentationModelKey, state);
    }

    public M getViewModel() {
        return viewModel;
    }
    public S getState() {
        return state;
    }
    @NonNull
    protected abstract M createViewModel();

    @NonNull
    protected abstract S createStateModel();

    private S restorePresentationModel(Class stateViewClass, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Serializable potentialPresentationModel =
                    savedInstanceState.getSerializable(presentationModelKey);
            try {
                return (S) potentialPresentationModel;
            } catch (ClassCastException ex) {
                throw new IllegalStateException(String.format(
                        "We expected a state saved in the bundle under the key: \"%s\", but was: %s",
                        presentationModelKey, potentialPresentationModel.toString()));
            }
        }
        return null;
    }
}
