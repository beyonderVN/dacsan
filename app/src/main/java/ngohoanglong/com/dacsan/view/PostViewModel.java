package ngohoanglong.com.dacsan.view;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import java.util.List;

import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public abstract class PostViewModel extends BaseViewModel {
    private static final String TAG = "PostViewModel";

    public final int LOADING_STATE = 0;
    public final int SHOW_STATE = 1;
    public final int EMPTY_STATE = 2;
    protected ObservableList<BaseHM> posts = new ObservableArrayList<>();
    private BehaviorSubject<Integer> viewState = BehaviorSubject.create(LOADING_STATE);
    private PublishSubject<Boolean> isLoadingMore = PublishSubject.create();

    public PostViewModel(ThreadScheduler threadScheduler,
                         Resources resources) {
        super(threadScheduler, resources);
    }

//      https://github.com/Froussios/Intro-To-RxJava/blob/master/Part%202%20-%20Sequence%20Basics/2.%20Reducing%20a%20sequence.md
//      https://github.com/Froussios/Intro-To-RxJava/blob/master/Part%201%20-%20Getting%20Started/2.%20Key%20types.md
    public Observable<Integer> getViewState() {
        return viewState.asObservable()
                .distinctUntilChanged()
                .doOnNext(integer -> Log.d(TAG, "getViewState: "+integer));
    }
    public Observable<Boolean> getIsLoadingMore() {
        return isLoadingMore.asObservable().distinctUntilChanged();
    }
    public ObservableList<BaseHM> getPosts() {
        return posts;
    }

    protected void removePost(int position) {
        posts.remove(position);
    }

    protected void showContentPage() {
        viewState.onNext(SHOW_STATE);
    }

    protected int indexOf(BaseHM post) {
        return posts.indexOf(post);
    }

    protected void showLoadingPage() {
        viewState.onNext(LOADING_STATE);
    }

    protected void showEmpty() {
        viewState.onNext(EMPTY_STATE);
    }

    protected void updatePosts(List<BaseHM> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
    }

    public void setPost(BaseHM post) {
        posts.set(indexOf(post), post);
    }

    public void addPost(BaseHM post) {
        posts.add(0, post);
    }

    protected void showLoadingMore() {
        this.posts.add(new LoadMoreHM());
        isLoadingMore.onNext(true);
    }

    protected void hideLoadingMore() {
        isLoadingMore.onNext(false);
        if (posts.get(posts.size() - 1) instanceof LoadMoreHM) {
            this.posts.remove(posts.size() - 1);
        }
    }
}