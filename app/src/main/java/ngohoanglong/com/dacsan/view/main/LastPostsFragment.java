package ngohoanglong.com.dacsan.view.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.ThreadSchedulerImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.BaseAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SimpleVerticalHM;
import ngohoanglong.com.dacsan.view.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastPostsFragment extends BaseFragment {
    private static final String TAG = "LastPostsFragment";
    LastPostsViewModel viewModel;


    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    BaseAdapter baseAdapter;

    public LastPostsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new LastPostsViewModel(new ThreadSchedulerImpl(AndroidSchedulers.mainThread(), Schedulers.io()), getActivity().getApplicationContext().getResources());
        View rootView = inflater.inflate(R.layout.fragment_last_post, container, false);
        ButterKnife.bind(this, rootView);
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
        baseAdapter = new BaseAdapter(getActivity(), new ArrayList<>(), new HolderFactoryImpl());
        rvPosts.setAdapter(baseAdapter);
        rvPosts.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvPosts.setHasFixedSize(true);
        rvPosts.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                Log.d(TAG, "onLoadMore: ");
//                isLoadingMore = true;
                try {
                    viewModel.loadMorePosts()
                            .takeUntil(stopEvent())
                            .doOnSubscribe(() -> isLoadingMore = true)
                            .doOnTerminate(() ->
                                    isLoadingMore = false)
                            .map(postVivmalls -> {
                                List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                                for (PostVivmall postVivmall : postVivmalls
                                        ) {
                                    Log.d(TAG, "bindViewModel>>map" + postVivmall.getProductName());
                                    baseHMs.add(new SimpleVerticalHM(postVivmall.getProductName()));
                                }
                                return baseHMs;
                            })
                            .subscribe(posts -> {
                                baseAdapter.addList(posts);
                                isLoadingMore = false;
                            }, throwable -> {
//                    ToastUtils.showLong(getContext(), throwable.getMessage());
                                Log.e(TAG, "bindViewModel: " + throwable.getMessage());
                                isLoadingMore = false;
                            });
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            boolean isLoadingMore = false;

            @Override
            public boolean isLoading() {

                Log.d(TAG, "isLoading: " + isLoadingMore);
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
        viewModel.loadPosts()
                .takeUntil(stopEvent())
                .doOnTerminate(() -> {
                })
                .map(postVivmalls -> {
                    List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                    for (PostVivmall postVivmall : postVivmalls
                            ) {
                        Log.d(TAG, "bindViewModel>>map" + postVivmall.getProductName());
                        baseHMs.add(new SimpleVerticalHM(postVivmall.getProductName()));
                    }
                    return baseHMs;
                })
                .subscribe(posts -> {
                    baseAdapter.addList(posts);
                }, throwable -> {
//                    ToastUtils.showLong(getContext(), throwable.getMessage());
                    Log.e(TAG, "bindViewModel: " + throwable.getMessage());
                });
    }
}
