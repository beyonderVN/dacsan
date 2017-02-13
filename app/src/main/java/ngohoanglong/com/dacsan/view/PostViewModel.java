package ngohoanglong.com.dacsan.view;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class PostViewModel extends BaseViewModel {
    protected final int SHOW_STATE = 0;
    protected final int LOADING_STATE = 1;
    protected final int EMPTY_STATE = 2;
    protected ObservableList<BaseHM> posts = new ObservableArrayList<>();
    private PublishSubject<Integer> viewState = PublishSubject.create();

    public PostViewModel(ThreadScheduler threadScheduler,
                         Resources resources) {
        super(threadScheduler, resources);
    }

    public Observable<Integer> getViewState() {
//      https://github.com/Froussios/Intro-To-RxJava/blob/master/Part%202%20-%20Sequence%20Basics/2.%20Reducing%20a%20sequence.md
        return viewState.asObservable().distinctUntilChanged();
    }

    public ObservableList<BaseHM> getPosts() {
        return posts;
    }

    protected void removePost(int position) {
        posts.remove(position);
    }

    protected void hideLoading() {
        viewState.onNext(LOADING_STATE);
    }

    protected int indexOf(BaseHM post) {
        return posts.indexOf(post);
    }

    protected void showLoading() {
        viewState.onNext(SHOW_STATE);
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
}