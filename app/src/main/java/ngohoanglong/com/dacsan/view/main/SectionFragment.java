package ngohoanglong.com.dacsan.view.main;

import android.animation.LayoutTransition;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import ngohoanglong.com.dacsan.databinding.FragmentSectionBinding;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.recyclerview.CenterLayoutManager;
import ngohoanglong.com.dacsan.utils.recyclerview.MumAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.SingleSelectedMumAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SectionHM;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.BaseViewHolder;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.TypeHolder;
import ngohoanglong.com.dacsan.view.BaseDelegateFragment;
import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import ngohoanglong.com.dacsan.view.delegate.StateDelegate;

/**
 * Created by Long on 2/27/2017.
 */

public class SectionFragment extends BaseDelegateFragment {
    private static final String TAG = "SectionFragment";

    private RxDelegate rxDelegate = new RxDelegate();
    ObservableArrayList<BaseHM> baseHMs2 = new ObservableArrayList<>();
    int selectedPosition =0;

    private StateDelegate stateDelegate = new StateDelegate() {
        @NonNull
        @Override
        protected SectionViewModel createViewModel() {
            return viewModel;
        }

        @NonNull
        @Override
        protected SectionViewModel.LastPostsState createStateModel() {
            return new SectionViewModel.LastPostsState(baseHMs,new ProductType());
        }
    };

    ObservableArrayList<BaseHM> baseHMs = new ObservableArrayList<>();

    {
        lifecycleDelegates.add(rxDelegate);
        lifecycleDelegates.add(stateDelegate);
    }

    @Inject
    SectionViewModel viewModel;

    private FragmentSectionBinding binding;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    @BindView(R.id.rvProductTypeList)
    RecyclerView rvProductTypeList;
    MumAdapter baseAdapter;
    SingleSelectedMumAdapter productTypeListAdapter;
    boolean isLoadingMore = false;
    @BindView(R.id.layoutWrap)
    ViewGroup layout;


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
        rootView = inflater.inflate(R.layout.fragment_section, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);
        binding.setViewModel(viewModel);
        setUpViews(rootView);
        return rootView;
    }

    private void setUpViews(View v) {

        LinearLayoutManager linearLayoutManager =
                new CenterLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 =
                new CenterLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        1, //The number of Columns in the grid
                        LinearLayoutManager.HORIZONTAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        staggeredGridLayoutManagerVertical.setItemPrefetchEnabled(false);
        baseAdapter = new MumAdapter(getActivity(),
                new HolderFactoryImpl(),
                viewModel.getPosts(),
                null);
        rvPosts.setAdapter(baseAdapter);
        rvPosts.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvPosts.setHasFixedSize(true);
        rvPosts.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                try {
                    viewModel.loadMore()
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
        rvPosts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = 0;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
                rvProductTypeList.smoothScrollToPosition(lastVisibleItemPosition);
                productTypeListAdapter.setSelectedPosition(lastVisibleItemPosition);
                productTypeListAdapter.notifyDataSetChanged();
            }
            public int getLastVisibleItem(int[] lastVisibleItemPositions) {
                int maxSize = 0;
                for (int i = 0; i < lastVisibleItemPositions.length; i++) {
                    if (i == 0) {
                        maxSize = lastVisibleItemPositions[i];
                    } else if (lastVisibleItemPositions[i] > maxSize) {
                        maxSize = lastVisibleItemPositions[i];
                    }
                }
                return maxSize;
            }
        });



        rvProductTypeList.setLayoutManager(linearLayoutManager);
        productTypeListAdapter = new SingleSelectedMumAdapter(getActivity(),
                new HolderFactoryImpl() {
                    private final int ITEM_TYPE = R.layout.layout_type;
                    @Override
                    public BaseViewHolder createHolder(int type, View view) {
                        switch(type) {
                            case ITEM_TYPE: return new TypeHolder(view);
                        }
                        return super.createHolder(type, view);
                    }

                    @Override
                    public int getType(SectionHM sectionHM) {
                        return ITEM_TYPE;
                    }
                },
                viewModel.getPosts(),
                (pos, baseHM) -> {
                    rvProductTypeList.smoothScrollToPosition(pos);
                    final RecyclerView.LayoutManager layoutManager = rvPosts.getLayoutManager();
                    if(layoutManager instanceof StaggeredGridLayoutManager){
                        ((StaggeredGridLayoutManager) rvPosts.getLayoutManager()).scrollToPositionWithOffset(pos, 0);
                    }else {
                        rvPosts.smoothScrollToPosition(pos);
                    }
                });

        rvProductTypeList.setAdapter(productTypeListAdapter);
        //fix no work well nest
        LayoutTransition layoutTransition = layout.getLayoutTransition();
        layoutTransition.setAnimateParentHierarchy(false);
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);

        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvPosts);
    }

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
    }

    protected void bindViewModel() {
        viewModel.bindViewModel();
        viewModel.getIsLoadingMore()
                .takeUntil(rxDelegate.stopEvent())
                .subscribe(aBoolean -> isLoadingMore = aBoolean);

        viewModel.loadFirst()
                .takeUntil(rxDelegate.stopEvent())
                .doOnTerminate(() -> {
                })
                .subscribe(baseHMs -> {
                });
    }
}
