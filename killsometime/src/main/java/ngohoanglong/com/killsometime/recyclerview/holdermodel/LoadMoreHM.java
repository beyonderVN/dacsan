package ngohoanglong.com.killsometime.recyclerview.holdermodel;


import ngohoanglong.com.killsometime.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 10/5/2016.
 */

public class LoadMoreHM extends BaseHM{
    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
