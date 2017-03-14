package ngohoanglong.com.dacsan.view.main;

import android.animation.LayoutTransition;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.databinding.FragmentLastPostBinding;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.recyclerview.CenterLayoutManager;
import ngohoanglong.com.dacsan.utils.recyclerview.MumAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.SingleSelectedMumAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;
import ngohoanglong.com.dacsan.view.BaseDelegateFragment;
import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import ngohoanglong.com.dacsan.view.delegate.StateDelegate;

/**
 * Created by Long on 2/27/2017.
 */

public class LastPostFragment extends BaseDelegateFragment {
    private static final String TAG = "LastPostFragment";

    private RxDelegate rxDelegate = new RxDelegate();
    ObservableArrayList<BaseHM> baseHMs2 = new ObservableArrayList<>();
    int selectedPosition =0;

    private StateDelegate productTypeStateDelegate = new StateDelegate() {
        @NonNull
        @Override
        protected ProductTypeViewModel createViewModel() {
            return productTypeViewModel;
        }

        @NonNull
        @Override
        protected ProductTypeViewModel.ProductTypeState createStateModel() {
            return new ProductTypeViewModel.ProductTypeState(
                    selectedPosition,
                    baseHMs2);
        }
    };

    private StateDelegate stateDelegate = new StateDelegate() {
        @NonNull
        @Override
        protected LastPostsViewModel createViewModel() {
            return viewModel;
        }

        @NonNull
        @Override
        protected LastPostsViewModel.LastPostsState createStateModel() {
            return new LastPostsViewModel.LastPostsState(baseHMs,productType);
        }
    };

    ObservableArrayList<BaseHM> baseHMs = new ObservableArrayList<>();
    ProductType productType = new ProductType();
    {
        lifecycleDelegates.add(rxDelegate);
        lifecycleDelegates.add(productTypeStateDelegate);
        lifecycleDelegates.add(stateDelegate);
    }

    @Inject
    LastPostsViewModel viewModel;
    @Inject
    ProductTypeViewModel productTypeViewModel;

    private FragmentLastPostBinding binding;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    @BindView(R.id.rvProductTypeList)
    RecyclerView rvProductTypeList;
    MumAdapter baseAdapter;
    SingleSelectedMumAdapter productTypeListAdapter;
    boolean isLoadingMore = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DacsanApplication.getAppComponent()
                .plus(new PostModule())
                .inject(this);
        super.onCreate(savedInstanceState);

    }
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_last_post, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);
        binding.setViewModel(viewModel);
        binding.setProductTypeViewModel(productTypeViewModel);
        setUpViews(rootView);
        return rootView;
    }

    private void setUpViews(View v) {
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        staggeredGridLayoutManagerVertical.setItemPrefetchEnabled(false);
        baseAdapter = new MumAdapter(getActivity(), new HolderFactoryImpl(),viewModel.getPosts(),null);
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
        rvPosts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 10) {
                    hideCatalogue();
                }
                if (dy < -10) {
                    showCatalogue();
                }
            }
        });



        LinearLayoutManager linearLayoutManager;
        linearLayoutManager =
                new CenterLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvProductTypeList.setLayoutManager(linearLayoutManager);
        productTypeListAdapter = new SingleSelectedMumAdapter(getActivity(),
                new HolderFactoryImpl(),
                productTypeViewModel.getPosts(),
                (pos, baseHM) -> {
                    Log.d(TAG, "setUpViews: ");
                    ((ProductTypeViewModel.ProductTypeState)productTypeViewModel.getState()).setSelectedPosition(pos);
                    viewModel.onChangeProductType(((ProductTypeHM) baseHM).getProductType());
                    rvProductTypeList.smoothScrollToPosition(((ProductTypeViewModel.ProductTypeState)productTypeViewModel.getState()).getSelectedPosition());

                    ;
                });

        rvProductTypeList.setAdapter(productTypeListAdapter);

        //fix no work well nest
        LayoutTransition layoutTransition = layout.getLayoutTransition();
        layoutTransition.setAnimateParentHierarchy(false);
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
    }
    @BindView(R.id.layoutWrap)
    ViewGroup layout;
    private void hideCatalogue() {
        if (rvProductTypeList.getVisibility() != View.GONE) {
            rvProductTypeList.setVisibility(View.GONE);
        }
    }

    private void showCatalogue() {
        if (rvProductTypeList.getVisibility() != View.VISIBLE) {
            rvProductTypeList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        bindViewModel();
        productTypeListAdapter.setSelectedPosition(((ProductTypeViewModel.ProductTypeState)productTypeViewModel.getState()).getSelectedPosition());
    }

    protected void bindViewModel() {
        productTypeViewModel.bindViewModel();
        productTypeViewModel.loadProductTypes()
                .takeUntil(rxDelegate.stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                });

        viewModel.bindViewModel();
        viewModel.getIsLoadingMore()
                .takeUntil(rxDelegate.stopEvent())
                .subscribe(aBoolean -> isLoadingMore = aBoolean);

        viewModel.loadFirstPosts()
                .takeUntil(rxDelegate.stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                });
    }
}
