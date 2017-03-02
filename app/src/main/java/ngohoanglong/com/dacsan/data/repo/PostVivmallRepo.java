package ngohoanglong.com.dacsan.data.repo;

import java.util.List;

import ngohoanglong.com.dacsan.data.repuest.ProductTypeRequest;
import ngohoanglong.com.dacsan.data.repuest.ProductsByTypeRequest;
import ngohoanglong.com.dacsan.data.request.LatestRequest;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.model.ProductType;
import rx.Observable;

/**
 * Created by Long on 2/8/2017.
 */

public interface PostVivmallRepo {
    Observable<List<PostVivmall>> getLatest(LatestRequest request);
    Observable<List<PostVivmall>> getProductsByType(ProductsByTypeRequest request);
    Observable<List<ProductType>> getProductsType(ProductTypeRequest request);
}
