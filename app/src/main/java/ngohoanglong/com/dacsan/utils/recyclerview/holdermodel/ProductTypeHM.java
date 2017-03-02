package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class ProductTypeHM extends BaseHM {
    int color = R.color.white;
    ProductType productType;

    public ProductTypeHM(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
