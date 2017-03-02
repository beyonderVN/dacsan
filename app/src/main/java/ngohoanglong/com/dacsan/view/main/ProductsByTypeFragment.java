package ngohoanglong.com.dacsan.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.databinding.FragmentLastPostBinding;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.utils.recyclerview.EndlessPostsAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.view.BaseDelegateFragment;
import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import ngohoanglong.com.dacsan.view.delegate.StateDelegate;

/**
 * Created by Long on 2/27/2017.
 */

public class ProductsByTypeFragment extends BaseDelegateFragment {
    private static final String TAG = "LastPostFragment";

    private RxDelegate rxDelegate = new RxDelegate();
    private StateDelegate stateDelegate = new StateDelegate() {

        @NonNull
        @Override
        protected ProductsByTypeViewModel createViewModel() {
            return viewModel;
        }

        @NonNull
        @Override
        protected ProductsByTypeViewModel.LastPostsState createStateModel() {
            return new ProductsByTypeViewModel.LastPostsState(new ArrayList<>());
        }
    };
    {
        lifecycleDelegates.add(rxDelegate);
        lifecycleDelegates.add(stateDelegate);
    }

    @Inject
    ProductsByTypeViewModel viewModel;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DacsanApplication.getAppComponent()
                .plus(new PostModule())
                .inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_products_by_type, container, false);
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
                            .takeUntil(rxDelegate.stopEvent())
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
    public void onStart() {
        super.onStart();
        bindViewModel();
    }

    protected void bindViewModel() {
        viewModel.bindViewModel();
        viewModel.getViewState()
                .takeUntil(rxDelegate.stopEvent())
                .subscribe(integer -> {
                    vaStateController.setDisplayedChild(integer);
                });
        viewModel.getIsLoadingMore()
                .takeUntil(rxDelegate.stopEvent())
                .subscribe(aBoolean -> isLoadingMore = aBoolean);
        viewModel.loadFirstPosts()
                .takeUntil(rxDelegate.stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                }) ;
    }
}
