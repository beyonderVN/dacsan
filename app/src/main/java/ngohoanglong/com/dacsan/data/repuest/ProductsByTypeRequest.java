package ngohoanglong.com.dacsan.data.repuest;

import ngohoanglong.com.dacsan.model.ProductType;

/**
 * Created by Long on 3/2/2017.
 */
public class ProductsByTypeRequest {
    ProductType productType;

    public ProductsByTypeRequest(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
