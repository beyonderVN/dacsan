package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.repo.PostVivmallRepoImpl;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.view.PostViewModel;
import rx.Observable;

/**
 * Created by Long on 2/13/2017.
 */

public class LastPostsViewModel extends PostViewModel {
    private static final String TAG = "LastPostsViewModel";
    int page=0;
    boolean isLoadingMore = false;
    PostVivmallRepo postRepo = new PostVivmallRepoImpl();
    public LastPostsViewModel(ThreadScheduler threadScheduler, Resources resources) {
        super(threadScheduler, resources);
    }

    public Observable<List<PostVivmall>> loadPosts() {
        return postRepo.getLatest(new LatestRequest(0))
                .delay(2, TimeUnit.SECONDS)
                .compose(withScheduler())
                .doOnSubscribe(() -> showLoading())
                .doOnNext(posts -> {
                    Log.d(TAG, "loadPosts: "+posts.size());
                    this.page = 0;
                    updatePosts(posts);
                    hideLoading();
                });
    }

    public Observable<List<PostVivmall>> loadMorePosts() {
        return postRepo.getLatest(new LatestRequest(page + 1))
                .compose(withScheduler())
                .doOnNext(posts -> {
                    Log.d(TAG, "loadMorePosts: ");
                    this.posts.addAll(posts);
                    this.page += 1;
                });
    }
}
