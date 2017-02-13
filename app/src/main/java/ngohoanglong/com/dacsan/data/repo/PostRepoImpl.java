package ngohoanglong.com.dacsan.data.repo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.Post;
import ngohoanglong.com.dacsan.utils.GetDataFromAssets;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public class PostRepoImpl implements PostRepo {
    private static final String TAG = "PostRepoImpl";
    @Override
    public Observable<List<Post>> getLatest(LatestRequest request) {
        List<Post> posts = new ArrayList<>();
        GetDataFromAssets getDataFromAssets =  new GetDataFromAssets();
        posts = getDataFromAssets.getPostList("posts.json", DacsanApplication.getAppContext());
        Log.d(TAG, "posts: " + posts.size());
        List<Post> finalPosts = posts;
        return Observable.create(subscriber -> {
            subscriber.onNext(finalPosts);
        });
    }
}
