package ngohoanglong.com.dacsan.view;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import ngohoanglong.com.dacsan.utils.ThreadScheduler;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseStateViewModel extends BaseViewModel {
    public BaseStateViewModel(@NonNull ThreadScheduler threadScheduler, @NonNull Resources resources) {
        super(threadScheduler, resources);
    }
    protected BaseState state;

    public BaseState getState() {
        return state;
    }

    public void setState(BaseState state) {
        this.state = state;
    }

    public abstract BaseState saveInstanceState();

    public abstract void returnInstanceState(BaseState instanceState);
    @Override
    public void bindViewModel() {
        returnInstanceState(state);
    }
}
