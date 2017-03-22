package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.request.ProductsByTypeRequest;
import ngohoanglong.com.dacsan.dependencyinjection.module.PostModule;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.MumAdapter;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SectionHM;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Long on 3/21/2017.
 */

public class SectionHolder extends BaseViewHolder<SectionHM> {
    private static final String TAG = "SectionHolder";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvListInSection)
    RecyclerView rvListInSection;

    @BindInt(R.integer.column_num)
    int columnNum;

    static int count = 0;

    int index ;
    public SectionHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        DacsanApplication.getAppComponent()
                .plus(new PostModule())
                .inject(this);
        index = count++;
    }

    @Inject
    PostVivmallRepo postVivmallRepo;
    @Inject
    ThreadScheduler threadScheduler;
    @Override
    public void bind(SectionHM item) {
        Log.d(TAG, "index: "+index);
        tvTitle.setText(item.getProductType().getProductTypeName());
        setUpViews(itemView, item);
        if(item.getBaseHMs().size()==0){
            postVivmallRepo.getLatest(new ProductsByTypeRequest(item.getProductType(), item.getPage()))
                    .compose(withScheduler())
                    .map(new Func1<List<PostVivmall>, List<BaseHM>>() {
                        @Override
                        public List<BaseHM> call(List<PostVivmall> postVivmalls) {
                            List<BaseHM> hmList = new ArrayList<BaseHM>();
                            for (PostVivmall postVivmall:postVivmalls
                                    ) {
                                hmList.add(new ProductItemHM(postVivmall));
                            }
                            return hmList;
                        }
                    })
                    .doOnSubscribe(()->{
                        isLoadingmore = true;
                        item.getBaseHMs().add(new LoadMoreHM());
                    })
                    .subscribe(new Subscriber<List<BaseHM>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(List<BaseHM> baseHMs) {
                            isLoadingmore = false;
                            if(item.getBaseHMs().size()>0){
                                BaseHM lastItem = item.getBaseHMs().get(item.getBaseHMs().size()-1);
                                if(lastItem instanceof LoadMoreHM){
                                    item.getBaseHMs().remove(item.getBaseHMs().size() - 1);
                                }
                            }

                            item.getBaseHMs().addAll(baseHMs);
                            item.upPage();
                        }
                    })
            ;
        }


    }

    boolean isLoadingmore = false;
    private void setUpViews(View v,SectionHM item) {
        isLoadingmore = false;
        if(item.getBaseHMs().size()>0){
            BaseHM lastItem = item.getBaseHMs().get(item.getBaseHMs().size()-1);
            if(lastItem instanceof LoadMoreHM){
                item.getBaseHMs().remove(item.getBaseHMs().size() - 1);
            }
        }

        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        staggeredGridLayoutManagerVertical.setItemPrefetchEnabled(false);
        MumAdapter baseAdapter = new MumAdapter(v.getContext(),
                new HolderFactoryImpl(),
                item.getBaseHMs(),
                null);
        rvListInSection.setAdapter(baseAdapter);
        rvListInSection.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvListInSection.setHasFixedSize(true);
        rvListInSection.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                if (mLayoutManager.getChildCount()<=0)return;
                try {
                    postVivmallRepo.getLatest(new ProductsByTypeRequest(item.getProductType(), item.getPage()))
                            .compose(withScheduler())
                            .map(postVivmalls -> {
                                List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                                for (PostVivmall baseHM : postVivmalls
                                        ) {
                                    baseHMs.add(new ProductItemHM(baseHM));
                                }
                                return baseHMs;
                            })
                            .doOnSubscribe(() -> {
                                item.getBaseHMs().add(new LoadMoreHM());
                                isLoadingmore = true;
                            })
                            .doOnNext(posts -> {
                                Log.d(TAG, "loadMorePosts: ");
                                isLoadingmore = false;
                                if(item.getBaseHMs().size()>0){
                                    BaseHM lastItem = item.getBaseHMs().get(item.getBaseHMs().size()-1);
                                    if(lastItem instanceof LoadMoreHM){
                                        item.getBaseHMs().remove(item.getBaseHMs().size() - 1);
                                    }
                                }

                                item.getBaseHMs().addAll(posts);
                                item.upPage();
                            })
                    .subscribe();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public boolean isLoading() {
                return isLoadingmore;
            }

            @Override
            public boolean isNoMore() {
                return false;
            }

        });
    }

    public <E> Observable.Transformer<E, E> withScheduler() {
        return observable -> observable.subscribeOn(threadScheduler.subscribeOn())
                .observeOn(threadScheduler.observeOn());
    }
}
