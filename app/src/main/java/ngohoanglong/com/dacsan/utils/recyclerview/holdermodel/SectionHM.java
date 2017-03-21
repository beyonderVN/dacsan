package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import android.databinding.ObservableArrayList;

import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.ProductType;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class SectionHM extends BaseHM {
    private boolean selected = false;
    private int color = R.color.aqua;
    private ProductType productType;
    int page = 0;
    public SectionHM(ProductType title, ObservableArrayList<BaseHM> baseHMs) {
        this.productType = title;
        this.baseHMs = baseHMs;
    }

    private ObservableArrayList<BaseHM> baseHMs;

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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ObservableArrayList<BaseHM> getBaseHMs() {
        return baseHMs;
    }

    public void setBaseHMs(ObservableArrayList<BaseHM> baseHMs) {
        this.baseHMs = baseHMs;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

}
