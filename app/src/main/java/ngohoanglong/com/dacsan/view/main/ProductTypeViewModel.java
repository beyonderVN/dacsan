package ngohoanglong.com.dacsan.view.main;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngohoanglong.com.dacsan.data.repo.PostVivmallRepo;
import ngohoanglong.com.dacsan.data.request.ProductTypeRequest;
import ngohoanglong.com.dacsan.dependencyinjection.ActivityScope;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.ThreadScheduler;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;
import ngohoanglong.com.dacsan.view.PostViewModel;
import rx.Observable;

/**
 * Created by Long on 2/13/2017.
 */
@ActivityScope
public class ProductTypeViewModel extends PostViewModel {
    private static final String TAG = "ProductTypeViewModel";

    PostVivmallRepo postRepo;

    @Inject
    public ProductTypeViewModel(ThreadScheduler threadScheduler,
                                Resources resources,
                                PostVivmallRepo postRepo
                              ) {
        super(threadScheduler, resources);
        this.postRepo = postRepo;
    }


    public boolean isNeedLoadFirst() {
        Log.d(TAG, "isNeedLoadFirst: " + posts.size());
        return posts.isEmpty() || posts == null ? true : false;
    }

    public Observable<List<BaseHM>> loadProductTypes() {
        Observable<List<BaseHM>> listObservable;
        if (isNeedLoadFirst()) {
            listObservable = postRepo.getProductsType(new ProductTypeRequest())
                    .compose(withScheduler())
                    .map((List<ProductType> productTypeList) -> {
                        List<BaseHM> baseHMs = new ArrayList<BaseHM>();
                        for (ProductType baseHM : productTypeList
                                ) {
                            baseHMs.add(new ProductTypeHM(baseHM));
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
                        Log.d(TAG, "loadProductTypes: doOnSubscribe");

                    })
                    .doOnNext(posts -> {
                        Log.d(TAG, "loadProductTypes: "+posts.size());
                        if (posts.size() > 0) {
                            updatePosts(posts);
                        } else {
                        }
                    });
    }


    @Override
    public void bindViewModel() {

    }

    public static class ProductTypeState extends PostsState {

        int selectedPosition;
        public int getSelectedPosition() {
            return selectedPosition;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        public ProductTypeState(int selectedPosition, ObservableArrayList<BaseHM> baseHMs) {
            super(baseHMs);
            this.selectedPosition = selectedPosition;

        }


    }
}
