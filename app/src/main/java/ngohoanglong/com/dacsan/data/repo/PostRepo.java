package ngohoanglong.com.dacsan.data.repo;

import java.util.List;

import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.Post;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public interface PostRepo {
    Observable<List<Post>> getLatest(LatestRequest request);
}
