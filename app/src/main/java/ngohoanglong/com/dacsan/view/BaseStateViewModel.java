package ngohoanglong.com.dacsan.view;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import ngohoanglong.com.dacsan.utils.ThreadScheduler;

/**
 * Created by Long on 2/27/2017.
 */

public abstract class BaseStateViewModel<S extends BaseState> extends BaseViewModel {
    public BaseStateViewModel(@NonNull ThreadScheduler threadScheduler, @NonNull Resources resources) {
        super(threadScheduler, resources);
    }
    protected S state;

    public S getState() {
        return state;
    }

    public void setState(S state) {
        this.state = state;
    }

    public abstract S saveInstanceState();

    public  void returnInstanceState(S instanceState){
        state = instanceState;
    };

}
