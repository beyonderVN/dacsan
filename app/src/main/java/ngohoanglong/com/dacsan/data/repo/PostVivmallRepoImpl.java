package ngohoanglong.com.dacsan.data.repo;

import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.data.request.ProductTypeRequest;
import ngohoanglong.com.dacsan.data.request.ProductsByTypeRequest;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.GetDataFromAssets;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public class PostVivmallRepoImpl implements PostVivmallRepo {
    private static final String TAG = "PostRepoImpl";
    static List<ProductType> finalPosts;
    @Override
    public Observable<List<PostVivmall>> getLatest(ProductsByTypeRequest request) {
        if(request.getProductType().getProductTypeVmall()==null){
            return  getAllLatest(request)
                    .delay(2, TimeUnit.SECONDS);
        }else{
            return getProductsByType(request)
                    .delay(2, TimeUnit.SECONDS);
        }

    }
    public Observable<List<PostVivmall>> getAllLatest(LatestRequest request) {
        if (finalPosts == null||finalPosts.size()==0) {
            finalPosts = GetDataFromAssets.getProductType("posts_vivmall2.json", DacsanApplication.getAppContext());
        }
        Log.d(TAG, "getLatest: "+finalPosts.get(request.getPage()%finalPosts.size()).getProductTypeName());
        List<PostVivmall> postVivmalls = GetDataFromAssets.getProductsByType("products_by_catalogue.json",
                finalPosts.get(request.getPage()%finalPosts.size()).getProductTypeVmall(),
                DacsanApplication.getAppContext()
        );
        for (PostVivmall postVivmall:postVivmalls
                ) {
            fillPostVivmall(postVivmall);
        }
        return Observable
                .create(subscriber -> {
                    subscriber.onNext(postVivmalls);
                    subscriber.onCompleted();
                })
                ;
    }

    static int count=0;
    private void fillPostVivmall(PostVivmall postVivmall) {
        Random random = new Random();
        double  v = random.nextInt(100) + 100;
        double  b = random.nextInt(100) + 100;
        double  p = random.nextInt(1000000) + 100000;
        postVivmall.setNumBuy(b);
        postVivmall.setNumView(v);
        postVivmall.setProductPrice(p);
        postVivmall.setProductName(postVivmall.getProductName()+" "+(++count));
    }

    @Override
    public Observable<List<PostVivmall>> getProductsByType(ProductsByTypeRequest request) {
        List<PostVivmall> postVivmalls = GetDataFromAssets.getProductsByType("products_by_catalogue.json",
                request.getProductType().getProductTypeVmall(),
                DacsanApplication.getAppContext()
        );
        for (PostVivmall postVivmall:postVivmalls
                ) {
            fillPostVivmall(postVivmall);
        }
        Log.d(TAG, "getProductsByType: "+request.getProductType().getProductTypeVmall()+": "+postVivmalls.size());
        return Observable
                .create(subscriber -> {
                    subscriber.onNext(postVivmalls);
                    subscriber.onCompleted();
                })
                ;
    }

    @Override
    public Observable<List<ProductType>> getProductsType(ProductTypeRequest request) {
        List<ProductType> finalPosts = GetDataFromAssets.getProductType("posts_vivmall2.json", DacsanApplication.getAppContext());
        return Observable
                .create(subscriber -> {
                    subscriber.onNext(finalPosts);
                    subscriber.onCompleted();
                })
                ;
    }


}
