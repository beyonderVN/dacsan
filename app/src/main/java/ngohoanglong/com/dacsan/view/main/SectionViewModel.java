package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.request.ProductTypeRequest;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SectionHM;
import ngohoanglong.com.dacsan.view.PostViewModel;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Long on 2/13/2017.
 */
@ActivityScope
public class SectionViewModel extends PostViewModel {
    private static final String TAG = "LastPostsViewModel";
    int page = 0;

    PostVivmallRepo postRepo;


    @Inject
    public SectionViewModel(ThreadScheduler threadScheduler,
                            Resources resources,
                            PostVivmallRepo postRepo
    ) {
        super(threadScheduler, resources);
        this.postRepo = postRepo;
    }


    public boolean isNeedLoadFirst() {
        Log.d(TAG, "isNeedLoadFirst: " + posts.size());
        return posts == null || posts.isEmpty();
    }

    protected PublishSubject<Boolean> refresh = PublishSubject.create();

    public Observable<List<BaseHM>> loadFirst() {
        Observable<List<BaseHM>> listObservable;
        if (isNeedLoadFirst()) {
            listObservable = postRepo.getProductsType(new ProductTypeRequest())
                    .compose(withScheduler())
                    .map(productTypeList -> {
                        List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                        for (ProductType baseHM : productTypeList
                                ) {
                            SectionHM sectionHM = new SectionHM(baseHM, new ObservableArrayList<BaseHM>());

                            baseHMs.add(sectionHM);
                        }
                        return baseHMs;
                    });

        } else {
            listObservable = Observable.just(posts)
                    .compose(withScheduler())
                    .map(posts -> {
                        List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                        for (BaseHM baseHM : posts
                                ) {
                            baseHMs.add(baseHM);
                        }
                        return baseHMs;
                    });
        }
        return listObservable
                .doOnSubscribe(() -> {
                    Log.d(TAG, "loadProductTypes: doOnSubscribe");
                })
                .doOnNext(posts -> {
                    Log.d(TAG, "loadProductTypes: "+posts.size());
                    if (posts.size() > 0) {
                        updatePosts(posts);
                    } else {
                    }
                });

    }

    public Observable<List<BaseHM>> loadMore() {
        return postRepo.getProductsType(new ProductTypeRequest())
                .delay(2, TimeUnit.SECONDS)
                .compose(withScheduler())
                .map(productTypeList -> {
                    List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                    for (ProductType baseHM : productTypeList
                            ) {
                        SectionHM sectionHM = new SectionHM(baseHM, new ObservableArrayList<BaseHM>());

                        baseHMs.add(sectionHM);
                    }
                    return baseHMs;
                })
                .takeUntil(refresh)
                .compose(withScheduler())
                .doOnSubscribe(() -> {
                    isLoadingMore.onNext(true);
                })
                .doOnNext(posts -> {
                    Log.d(TAG, "loadMorePosts: ");
                    isLoadingMore.onNext(false);
                    this.posts.addAll(posts);
                    this.page += 1;
                })
                ;
    }


    @Override
    public void bindViewModel() {

    }

    public static class LastPostsState extends PostsState {
        ProductType productType;

        public LastPostsState(ObservableArrayList<BaseHM> baseHMs, ProductType productType) {
            super(baseHMs);
            this.productType = productType;
        }

        public ProductType getProductType() {
            return productType;
        }

        public void setProductType(ProductType productType) {
            this.productType = productType;
        }

    }

}
