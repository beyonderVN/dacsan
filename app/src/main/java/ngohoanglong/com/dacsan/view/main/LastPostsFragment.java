package ngohoanglong.com.dacsan.view.main;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.databinding.FragmentLastPostBinding;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.EndlessPostsAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.view.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastPostsFragment extends BaseFragment {
    private static final String TAG = "LastPostsFragment";
    private FragmentLastPostBinding binding;
    LastPostsViewModel viewModel;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    EndlessPostsAdapter baseAdapter;
    boolean isLoadingMore = false;
    @BindView(R.id.vaStateController)
    ViewAnimator vaStateController;

    public LastPostsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new LastPostsViewModel(new ThreadSchedulerImpl(AndroidSchedulers.mainThread(), Schedulers.io()), getActivity().getApplicationContext().getResources());
        View rootView = inflater.inflate(R.layout.fragment_last_post, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);
        binding.setViewModel(viewModel);
        setUpViews();
        return rootView;
    }

    private void setUpViews() {
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        baseAdapter = new EndlessPostsAdapter(getActivity(), new HolderFactoryImpl());
        rvPosts.setAdapter(baseAdapter);
        rvPosts.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvPosts.setHasFixedSize(true);
        rvPosts.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                try {
                    viewModel.loadMorePosts()
                            .takeUntil(stopEvent())
                            .subscribe(baseHMs -> {
                            })
                    ;
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public boolean isLoading() {
                return isLoadingMore;
            }

            @Override
            public boolean isNoMore() {
                return false;
            }
        });
    }


    @Override
    protected void bindViewModel() {
        viewModel.getViewState()
                .takeUntil(stopEvent())
                .subscribe(integer -> {
                    vaStateController.setDisplayedChild(integer);
                });
        viewModel.getIsLoadingMore()
                .takeUntil(stopEvent())
                .subscribe(aBoolean -> isLoadingMore = aBoolean);
        viewModel.loadFirstPosts()
                .takeUntil(stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                })
        ;


    }

}
