package ngohoanglong.com.dacsan.view;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class PostViewModel extends BaseStateViewModel {
    private static final String TAG = "PostViewModel";

    protected ObservableArrayList<BaseHM> posts = new ObservableArrayList<>();
    protected BehaviorSubject<Boolean> isLoadingMore = BehaviorSubject.create(false);

    public PostViewModel(ThreadScheduler threadScheduler,
                         Resources resources) {
        super(threadScheduler, resources);
    }

    public Observable<Boolean> getIsLoadingMore() {
        return isLoadingMore.asObservable().distinctUntilChanged()
                .doOnNext(aBoolean -> {
                    if (aBoolean){
                        showLoadingMore();
                    }else {
                        hideLoadingMore();
                    }
                });
    }
    public ObservableList<BaseHM> getPosts() {
        return posts;
    }

    protected void removePost(int position) {
        posts.remove(position);
    }

    protected int indexOf(BaseHM post) {
        return posts.indexOf(post);
    }

    protected void updatePosts(List<BaseHM> posts) {
        if (this.posts == null) {
            this.posts = new ObservableArrayList<>();
        }
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
    }

    protected void hideLoadingMore() {
        if(posts.size()==0)return;
        if (posts.get(posts.size() - 1) instanceof LoadMoreHM) {
            this.posts.remove(posts.size() - 1);
        }
    }


}