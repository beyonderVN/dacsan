package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.view.BaseViewModel;
import rx.Observable;

/**
 * Created by Long on 2/21/2017.
 */

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull ThreadScheduler threadScheduler, @NonNull Resources resources) {
        super(threadScheduler, resources);

    }

    @Override
    public void bindViewModel() {

    }

    public Observable<Boolean> loginIsSuccess() {
        return DacsanApplication.authManager.isLogin()
                .compose(withScheduler());
    }
}
