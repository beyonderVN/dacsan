package ngohoanglong.com.dacsan.view.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import ngohoanglong.com.dacsan.view.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastPostsFragment extends BaseFragment {
    private static final String TAG = "LastPostsFragment";
    LastPostsViewModel viewModel;
    public LastPostsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new LastPostsViewModel(new ThreadSchedulerImpl(AndroidSchedulers.mainThread(), Schedulers.io()), getActivity().getApplicationContext().getResources());
        return inflater.inflate(R.layout.fragment_last_post, container, false);

    }
    private void setUpViews() {

    }


    @Override
    protected void bindViewModel() {
        viewModel.loadPosts()
                .takeUntil(stopEvent())
                .doOnTerminate(() -> {

                })
                .subscribe(posts -> {}, throwable -> {
//                    ToastUtils.showLong(getContext(), throwable.getMessage());
                    Log.e(TAG, "bindViewModel: "+throwable.getMessage() );
                });
    }
}
