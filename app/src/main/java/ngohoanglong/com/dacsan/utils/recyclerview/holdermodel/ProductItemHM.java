package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import com.vnwarriors.advancedui.appcore.common.DynamicHeightImageView;

import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class ProductItemHM extends BaseHM implements DynamicHeightImageView.SetImageRatio {
    PostVivmall postVivmall;
    double imageRatio = 0;
    public ProductItemHM(PostVivmall postVivmall) {
        this.postVivmall = postVivmall;
    }

    public PostVivmall getPostVivmall() {
        return postVivmall;
    }

    public void setPostVivmall(PostVivmall postVivmall) {
        this.postVivmall = postVivmall;
    }

    public double getImageRatio() {
        return imageRatio;
    }

    public void setImageRatio(double imageRatio) {
        this.imageRatio = imageRatio;
    }

    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

    @Override
    public String toString() {
        return "ProductItemHM{" +
                "postVivmall=" + postVivmall +
                ", imageRatio=" + imageRatio +
                '}';
    }
}
