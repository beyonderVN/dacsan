package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.repo.PostVivmallRepoImpl;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.view.PostViewModel;
import rx.Observable;

/**
 * Created by Long on 2/13/2017.
 */

public class LastPostsViewModel extends PostViewModel {
    private static final String TAG = "LastPostsViewModel";
    int page = 0;

    PostVivmallRepo postRepo = new PostVivmallRepoImpl();

    public LastPostsViewModel(ThreadScheduler threadScheduler, Resources resources) {
        super(threadScheduler, resources);
        Log.d(TAG, "LastPostsViewModel: ");
    }

    public boolean isNeedLoadFirst() {
        Log.d(TAG, "isNeedLoadFirst: " + posts.size());
        return posts.isEmpty() || posts == null ? true : false;
    }

    public Observable<List<BaseHM>> loadFirstPosts() {
        Observable<List<BaseHM>> listObservable;
        if (isNeedLoadFirst()) {
            listObservable = postRepo.getLatest(new LatestRequest(0))
                    .compose(withScheduler())
                    .map(postVivmalls -> {
                        List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                        for (PostVivmall baseHM : postVivmalls
                                ) {
                            baseHMs.add(new ProductItemHM(baseHM));
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
                        Log.d(TAG, "loadFirstPosts: doOnSubscribe");
                        showLoadingPage();
                    })
                    .doOnNext(posts -> {
                        this.page = 0;
                        if (posts.size() > 0) {
                            updatePosts(posts);
                            showContentPage();
                        } else {
                            showEmpty();
                        }
                    });

    }

    public Observable<List<BaseHM>> loadMorePosts() {
        return postRepo.getLatest(new LatestRequest(page + 1))
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
                    showLoadingMore();
                })
                .doOnNext(posts -> {
                    Log.d(TAG, "loadMorePosts: ");
                    hideLoadingMore();
                    this.posts.addAll(posts);
                    this.page += 1;
                })
                ;
    }


    public Serializable getInstanceState() {
        hideLoadingMore();
        return new LastPostsState(posts);
    }

    public void setInstanceState(LastPostsState instanceState) {
        updatePosts(instanceState.getBaseHMs());
    }

    public static class LastPostsState implements Serializable {
        List<BaseHM> baseHMs;

        public LastPostsState() {
        }

        public LastPostsState(List<BaseHM> baseHMs) {
            this.baseHMs = baseHMs;
        }

        public List<BaseHM> getBaseHMs() {
            return baseHMs;
        }

        public void setBaseHMs(List<BaseHM> baseHMs) {
            this.baseHMs = baseHMs;
        }
    }
}
