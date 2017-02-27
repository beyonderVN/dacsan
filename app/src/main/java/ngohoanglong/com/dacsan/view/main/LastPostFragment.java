package ngohoanglong.com.dacsan.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.data.repo.PostVivmallRepoImpl;
import ngohoanglong.com.dacsan.databinding.FragmentLastPostBinding;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.EndlessPostsAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.view.BaseDelegateRxFragmentWithState;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Long on 2/27/2017.
 */

public class LastPostFragment extends BaseDelegateRxFragmentWithState<LastPostsViewModel,LastPostsViewModel.LastPostsState> {
    private static final String TAG = "LastPostFragment";
    private FragmentLastPostBinding binding;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    EndlessPostsAdapter baseAdapter;
    boolean isLoadingMore = false;
    @BindView(R.id.vaStateController)
    ViewAnimator vaStateController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_last_post, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);
        binding.setViewModel(getViewModel());
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
                    getViewModel().loadMorePosts()
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

    @NonNull
    @Override
    protected LastPostsViewModel createViewModel() {
        LastPostsViewModel lastPostsViewModel =
                new LastPostsViewModel(
                        new ThreadSchedulerImpl(AndroidSchedulers.mainThread(),
                                Schedulers.io()),
                        DacsanApplication.getAppContext().getResources(),
                        new PostVivmallRepoImpl()
                );
        return lastPostsViewModel;
    }

    @NonNull
    @Override
    protected LastPostsViewModel.LastPostsState createPresentationModel() {
        return new LastPostsViewModel.LastPostsState(new ArrayList<>());
    }

    @Override
    protected void bindViewModel() {
        getViewModel().bindViewModel();
        getViewModel().getViewState()
                .takeUntil(stopEvent())
                .subscribe(integer -> {
                    vaStateController.setDisplayedChild(integer);
                });
        getViewModel().getIsLoadingMore()
                .takeUntil(stopEvent())
                .subscribe(aBoolean -> isLoadingMore = aBoolean);
        getViewModel().loadFirstPosts()
                .takeUntil(stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                }) ;
    }
}
