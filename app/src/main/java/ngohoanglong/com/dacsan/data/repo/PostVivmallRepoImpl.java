package ngohoanglong.com.dacsan.data.repo;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.GetDataFromAssets;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public class PostVivmallRepoImpl implements PostVivmallRepo {
    private static final String TAG = "PostRepoImpl";
    @Override
    public Observable<List<PostVivmall>> getLatest(LatestRequest request) {
        List<PostVivmall> posts = new ArrayList<PostVivmall>();
        GetDataFromAssets<PostVivmall> getDataFromAssets =  new GetDataFromAssets<PostVivmall>();
        posts = getDataFromAssets.getPostList("posts_vivmall2.json", DacsanApplication.getAppContext());
        List<PostVivmall> finalPosts = posts;
        return Observable
                .create(subscriber -> {
            subscriber.onNext(finalPosts);
            subscriber.onCompleted();
        })
               ;
    }
}
