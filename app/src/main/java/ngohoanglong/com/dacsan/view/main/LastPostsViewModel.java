package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.request.ProductsByTypeRequest;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.view.PostViewModel;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Long on 2/13/2017.
 */
@ActivityScope
public class LastPostsViewModel extends PostViewModel {
    private static final String TAG = "LastPostsViewModel";
    int page = 0;

    PostVivmallRepo postRepo;


    @Inject
    public LastPostsViewModel(ThreadScheduler threadScheduler,
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

    public Observable<List<BaseHM>> loadFirstPosts() {
        Observable<List<BaseHM>> listObservable;
        if (isNeedLoadFirst()) {
            listObservable = postRepo.getLatest(new ProductsByTypeRequest(((LastPostsState) getState()).getProductType(), 0))
                    .takeUntil(refresh)
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
                        Log.d(TAG, "loadFirstPosts: doOnSubscribe");
                    })
                    .doOnNext(posts -> {

                        if (posts.size() > 0) {
                            this.page++;
                            updatePosts(posts);

                        } else {

                        }
                    });
            ;

        } else {
            listObservable = Observable.just(posts)
                    .takeUntil(refresh)
                    .compose(withScheduler())
                    .map(posts -> {
                        List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                        for (BaseHM baseHM : posts
                                ) {
                            baseHMs.add(baseHM);
                        }
                        return baseHMs;
                    })
                    .doOnNext(baseHMs -> {

                    });
        }
        return listObservable;

    }

    public void onChangeProductType(ProductType productType) {
        ((LastPostsState) getState()).setProductType(productType);
        refresh.onNext(true);
        isLoadingMore.onNext(false);
        posts.clear();
    }

    public Observable<List<BaseHM>> loadMorePosts() {
        return postRepo.getLatest(new ProductsByTypeRequest(((LastPostsState) getState()).getProductType(), page + 1))
                .takeUntil(refresh)
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
