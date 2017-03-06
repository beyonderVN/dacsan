package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class ProductTypeHM extends BaseHM {
    boolean selected = false;
    ProductType productType;
    int color = R.color.aqua;
    public ProductTypeHM(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
