package ngohoanglong.com.dacsan;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.model.Post;
import ngohoanglong.com.dacsan.utils.AssetsUtils;

/**
 * Created by Long on 2/10/2017.
 */

public class DacsanApplication extends Application {
    private static final String TAG = "DacsanApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        List<Post> posts = new ArrayList<>();
        posts = AssetsUtils.getPostList("posts.json", this);
        Log.d(TAG, "posts: " + posts.size());
    }
}
