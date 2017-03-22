package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

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
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvListInSection)
    RecyclerView rvListInSection;

    @BindInt(R.integer.column_num)
    int columnNum;

    public SectionHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        DacsanApplication.getAppComponent()
                .plus(new PostModule())
                .inject(this);
    }

    @Inject
    PostVivmallRepo postVivmallRepo;
    @Inject
    ThreadScheduler threadScheduler;
    @Override
    public void bind(SectionHM item) {

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
                            if(item.getBaseHMs().size()>0){
                                BaseHM lastItem = item.getBaseHMs().get(item.getBaseHMs().size()-1);
                                if(lastItem instanceof LoadMoreHM){
                                    item.getBaseHMs().remove(item.getBaseHMs().size() - 1);
                                }
                            }

                            item.getBaseHMs().addAll(baseHMs);

                        }
                    })
            ;
        }


    }


    private void setUpViews(View v,SectionHM item) {
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
        rvListInSection.setNestedScrollingEnabled(false);
    }

    public <E> Observable.Transformer<E, E> withScheduler() {
        return observable -> observable.subscribeOn(threadScheduler.subscribeOn())
                .observeOn(threadScheduler.observeOn());
    }
}
