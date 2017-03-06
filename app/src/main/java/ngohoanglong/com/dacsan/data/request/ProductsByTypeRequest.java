package ngohoanglong.com.dacsan.data.request;

import ngohoanglong.com.dacsan.model.ProductType;

/**
 * Created by Long on 3/2/2017.
 */
public class ProductsByTypeRequest extends LatestRequest {
    ProductType productType;

    public ProductsByTypeRequest(ProductType productType, int page) {
        super(page);
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
