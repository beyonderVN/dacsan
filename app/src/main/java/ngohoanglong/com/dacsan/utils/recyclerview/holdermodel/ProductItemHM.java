package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class ProductItemHM extends BaseHM {
    PostVivmall postVivmall;

    public ProductItemHM(PostVivmall postVivmall) {
        this.postVivmall = postVivmall;
    }

    public PostVivmall getPostVivmall() {
        return postVivmall;
    }

    public void setPostVivmall(PostVivmall postVivmall) {
        this.postVivmall = postVivmall;
    }

    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

    @Override
    public String toString() {
        return "ProductItemHM{" +
                "postVivmall=" + postVivmall +
                '}';
    }
}
